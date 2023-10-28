package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityBondDao
import com.aredruss.warmaster.domain.database.dao.DatasheetAbilityDao
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetRuleDao
import com.aredruss.warmaster.domain.database.dao.InvSaveDao
import com.aredruss.warmaster.domain.database.dao.KeywordsDao
import com.aredruss.warmaster.domain.database.dao.MiniatureDao
import com.aredruss.warmaster.domain.database.dao.MiniatureKeywordDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao
import com.aredruss.warmaster.domain.database.index.IndexedComposition
import com.aredruss.warmaster.domain.database.index.IndexedUnitComposition
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature

class UnitInfoRepository(
    private val datasheetDao: DatasheetDao,
    private val miniatureDao: MiniatureDao,
    private val invSaveDao: InvSaveDao,
    private val datasheetRuleDao: DatasheetRuleDao,
    private val datasheetAbilityDao: DatasheetAbilityDao,
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

    suspend fun getIndexedUnitComposition(
        miniatures: List<Miniature>,
        datasheetId: String
    ): List<IndexedComposition> {
        val names = miniatures.map { it.name }
        val compositions = emptyList<IndexedComposition>().toMutableList()
        val mappedIdCompositions = emptyMap<String, List<UnitCompositionMiniature>>().toMutableMap()

        // We get unit_compositions for each UC variant (X points, 2X points, etc)
        val unitCompositionsMap = unitCompositionDao
            .getItemsById(datasheetId)
            .groupBy {
                it.displayOrder
            }

        miniatures.forEach { item ->
            val miniCompositions = unitCompositionMiniatureDao.getItemsByMini(item.id)
            mappedIdCompositions[item.id] = miniCompositions
        }

        unitCompositionsMap.forEach { (order, unitCompositions) ->
            compositions.add(
                IndexedComposition(
                    order = order,
                    miniatures = names,
                    //Everything is heavily dependent on the indexes and everything being in order
                    unitCompositionMiniature = mappedIdCompositions
                        .map {
                            it.value[(order - 1).toInt()]
                        }
                        .map {
                            it.getReadableRange()
                        },
                    unitComposition = IndexedUnitComposition(
                        pointCosts = unitCompositions.first().points,
                        isDefault = unitCompositions.first().isDefault
                    )
                )
            )
        }
        return compositions
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