package com.aredruss.warmaster.domain

import android.content.Context
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.model.MetaData
import com.aredruss.warmaster.domain.database.model.WarhammerData
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import timber.log.Timber

class InfoRepository(
    context: Context,
    private val prefs: WarmasterPrefs,
    private val datasheetDao: DatasheetDao,
    private val miniatureDao: MiniatureDao,
    private val invSaveDao: InvSaveDao,
    private val datasheetRuleDao: DatasheetRuleDao,
    private val datasheetAbilityDao: DatasheetAbilityDao,
    private val factionKeywordDao: FactionKeywordDao,
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao,
    private val unitCompositionMiniatureDao: UnitCompositionMiniatureDao,
    private val unitCompositionDao: UnitCompositionDao,
    private val datasheetAbilityBondDao: DatasheetAbilityBondDao,
    private val miniatureKeywordsDao: MiniatureKeywordDao,
    private val keywordsDao: KeywordsDao
) {

    private val json = Json { ignoreUnknownKeys = true }
    private val metaDataFile = context.resources.assets.open("metadata.json")
    private val dataFile = context.resources.assets.open("data.json")

    suspend fun parseDataFile(): Result<Boolean> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            if (isDataUploadRequired()) {
                val jsonData = json.decodeFromString(
                    WarhammerData.serializer(),
                    dataFile.bufferedReader().use {
                        it.readText()
                    }
                )
                dataFile.close()
                populateDatabase(jsonData)
            } else {
                true
            }
        }.onFailure {
            Timber.e(it)
        }
    }

    private suspend fun isDataUploadRequired(): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val metadata = json.decodeFromString(
                MetaData.serializer(),
                metaDataFile.bufferedReader().use {
                    it.readText()
                }
            )
            (metadata.dataVersion > prefs.currentDataVersion).also {
                prefs.currentDataVersion = metadata.dataVersion
            }
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun populateDatabase(data: WarhammerData): Boolean =
        withContext(Dispatchers.IO) {
            data.apply {
                datasheetDao.insert(datasheets)
                miniatureDao.insert(miniatures)
                datasheetFactionKeywordDao.insert(datasheetFactionKeywords)
                factionKeywordDao.insert(factionKeywords)

                invSaveDao.insert(invSaves)
                datasheetAbilityDao.insert(datasheetAbilities)
                unitCompositionDao.insert(unitComposition)
                unitCompositionMiniatureDao.insert(unitCompositionMiniature)
                datasheetRuleDao.insert(datasheetRule + datasheetDamageRule)
                datasheetAbilityBondDao.insert(datasheetAbilityBonds)

                keywordsDao.insert(keywords)
                miniatureKeywordsDao.insert(miniatureKeywords)
            }
            return@withContext true
        }
}