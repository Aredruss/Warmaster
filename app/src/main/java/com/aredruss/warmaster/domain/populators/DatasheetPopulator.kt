package com.aredruss.warmaster.domain.populators

import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.model.Datasheet
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetAbilityBond
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Keyword
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.MiniatureKeyword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class DatasheetPopulator(
    private val datasheetDao: DatasheetDao,
    private val miniatureDao: MiniatureDao,
    private val invSaveDao: InvSaveDao,
    private val datasheetRuleDao: DatasheetRuleDao,
    private val datasheetAbilityDao: DatasheetAbilityDao,
    private val datasheetAbilityBondDao: DatasheetAbilityBondDao,
    private val miniatureKeywordsDao: MiniatureKeywordDao,
    private val keywordsDao: KeywordsDao,
) {

    suspend fun insertDatasheet(data: List<Datasheet>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetDao.insert(data)
        }

    suspend fun insertMiniatures(data: List<Miniature>) =
        withContext(Dispatchers.IO + Job()) {
            miniatureDao.insert(data)
        }

    suspend fun insertInvSave(data: List<InvSave>) =
        withContext(Dispatchers.IO + Job()) {
            invSaveDao.insert(data)
        }

    suspend fun insertDatasheetRule(data: List<DatasheetRule>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetRuleDao.insert(data)
        }

    suspend fun insertDatasheetAbility(data: List<DatasheetAbility>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetAbilityDao.insert(data)
        }

    suspend fun insertDatasheetAbilityBond(data: List<DatasheetAbilityBond>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetAbilityBondDao.insert(data)
        }

    suspend fun insertMiniatureKeyword(data: List<MiniatureKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            miniatureKeywordsDao.insert(data)
        }

    suspend fun insertKeywords(data: List<Keyword>) =
        withContext(Dispatchers.IO + Job()) {
            keywordsDao.insert(data)
        }

}