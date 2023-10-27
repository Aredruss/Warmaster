package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
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
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.Keyword
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature

class UnitInfoRepository(
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
    private val miniatureKeywordDao: MiniatureKeywordDao,
    private val keywordsDao: KeywordsDao
) {
    suspend fun getDatasheetById(id: String) = datasheetDao.getItemById(id)

    suspend fun getMiniaturesByDatasheetId(datasheetId: String) =
        miniatureDao.getItemsByDatasheet(datasheetId)

    suspend fun getInvSaveForUnit(datasheetId: String) = invSaveDao.getItemById(datasheetId)

    suspend fun getDatasheetRules(datasheetId: String) = datasheetRuleDao.getItemById(datasheetId)

    suspend fun getUnitComposition(datasheetId: String) =
        unitCompositionDao.getItemById(datasheetId)

    suspend fun getUnitCompositionsMiniatureByDatasheet(datasheetId: String): List<UnitCompositionMiniature> {
        val unitCompositions = getUnitComposition(datasheetId)
            .map {
                it.id
            }
        return unitCompositionMiniatureDao.getItemsById(unitCompositions)
    }

    suspend fun getAbilitiesByDatasheet(datasheetId: String): List<DatasheetAbility> {
        val bonds = datasheetAbilityBondDao
            .getItemsById(datasheetId)
            .map {
                it.datasheetAbilityId
            }
        return datasheetAbilityDao.getItemsById(bonds)
    }

    suspend fun getMiniatureKeywords(miniatureId: String): List<String> {
        val miniatureKeywordIds = miniatureKeywordDao
            .getItemsById(miniatureId)
            .map {
                it.keywordId
            }
        return keywordsDao.getItemsById(miniatureKeywordIds).map { it.name }
    }
}