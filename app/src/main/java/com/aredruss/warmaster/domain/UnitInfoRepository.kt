package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.DatasheetSubAbilityDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.dao.PublicationDao
import com.aredruss.warmaster.domain.database.dao.RuleContainerComponentDao
import com.aredruss.warmaster.domain.database.index.AggregatedAbilities
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class UnitInfoRepository(
    private val datasheetDao: DatasheetDao,
    private val miniatureDao: MiniatureDao,
    private val invSaveDao: InvSaveDao,
    private val datasheetRuleDao: DatasheetRuleDao,
    private val datasheetAbilityDao: DatasheetAbilityDao,
    private val datasheetAbilityBondDao: DatasheetAbilityBondDao,
    private val datasheetSubAbilityDao: DatasheetSubAbilityDao,
    private val miniatureKeywordDao: MiniatureKeywordDao,
    private val keywordsDao: KeywordsDao,
    private val ruleContainerComponentDao: RuleContainerComponentDao,
    private val publicationDao: PublicationDao
) {
    suspend fun getDatasheetById(id: String) = withContext(Dispatchers.IO + Job()) {
        datasheetDao.getItemById(
            id
        )
    }

    suspend fun getMiniaturesByDatasheetId(datasheetId: String) =
        withContext(Dispatchers.IO + Job()) {
            miniatureDao.getItemsByDatasheet(datasheetId)
        }

    suspend fun getInvSaveForUnit(datasheetId: String) =
        withContext(Dispatchers.IO + Job()) {
            invSaveDao.getItemById(datasheetId)
        }

    suspend fun getDatasheetRules(datasheetId: String) =
        withContext(Dispatchers.IO + Job()) { datasheetRuleDao.getItemById(datasheetId) }

    suspend fun getFactionId(publicationId: String) =
        withContext(Dispatchers.IO + Job()) {
            publicationDao.getFactionKeywordId(publicationId)
        }

    suspend fun getMiniatureKeywords(miniatureId: String): List<String> =
        withContext(Dispatchers.IO + Job()) {
            val miniatureKeywordIds = miniatureKeywordDao
                .getItemsById(miniatureId)
            return@withContext keywordsDao.getItemsById(miniatureKeywordIds).map { it.name }
        }

    suspend fun getDatasheetAbilities(datasheetId: String): AggregatedAbilities =
        withContext(Dispatchers.IO + Job()) {
            val abilities = getAbilitiesByDatasheet(datasheetId)
            val datasheetAbilities = abilities.filter { it.abilityType == "datasheet" }

            val subAbilities = mutableListOf<DatasheetSubAbility>()

            datasheetAbilities.forEach {
                val subs = datasheetSubAbilityDao.getItemsById(it.id)
                subAbilities.addAll(subs)
            }

            val extraAbilities = datasheetAbilities.associateWith { dAbility ->
                subAbilities.filter { sAbility ->
                    sAbility.datasheetAbilityId == dAbility.id
                }
            }.filter {
                it.value.isNotEmpty()
            }

            return@withContext AggregatedAbilities(
                normalAbilities = abilities.filter { it.armyRuleId == null }
                    .groupBy { it.abilityType },
                subNormalAbilities = extraAbilities.map {
                    it.key.name to it.value
                }.toMap(),
                factionAbilities = abilities.filter {
                    it.armyRuleId != null
                }.map {
                    it to ruleContainerComponentDao.getItemsById(it.armyRuleId ?: "0")
                }
            )
        }

    private suspend fun getAbilitiesByDatasheet(datasheetId: String): List<DatasheetAbility> =
        withContext(Dispatchers.IO + Job()) {
            val bonds = datasheetAbilityBondDao
                .getItemsById(datasheetId)
            return@withContext datasheetAbilityDao.getItemsById(bonds)
        }
}