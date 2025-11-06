package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao
import com.aredruss.warmaster.domain.database.index.IndexedComposition
import com.aredruss.warmaster.domain.database.index.IndexedUnitComposition
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature

class UnitCompositionRepository(
    private val unitCompositionDao: UnitCompositionDao,
    private val unitCompositionMiniatureDao: UnitCompositionMiniatureDao
) {

    suspend fun getIndexedUnitComposition(
        miniatures: List<Miniature>,
        datasheetId: String
    ): List<IndexedComposition> {
        val names = miniatures.map { it.name }
        val compositions = emptyList<IndexedComposition>().toMutableList()
        val mappedIdCompositions = emptyMap<String, List<UnitCompositionMiniature>>()
            .toSortedMap()
            .toMutableMap()

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
                            if (it.value.size == 1) {
                                it.value.first()
                            } else {
                                it.value.reversed()[(order - 1).toInt()]
                            }
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
}