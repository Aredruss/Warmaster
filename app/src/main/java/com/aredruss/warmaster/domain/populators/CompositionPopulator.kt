package com.aredruss.warmaster.domain.populators

import com.aredruss.warmaster.domain.database.dao.UnitCompositionDao
import com.aredruss.warmaster.domain.database.dao.UnitCompositionMiniatureDao
import com.aredruss.warmaster.domain.database.model.UnitComposition
import com.aredruss.warmaster.domain.database.model.UnitCompositionMiniature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class CompositionPopulator(
    private val unitCompositionMiniatureDao: UnitCompositionMiniatureDao,
    private val unitCompositionDao: UnitCompositionDao,
) {
    suspend fun insertUnitComposition(unitCompositions: List<UnitComposition>) =
        withContext(Dispatchers.IO + Job()) {
            unitCompositionDao.insert(unitCompositions)
        }

    suspend fun insertUnitCompositionMiniature(
        unitCompositionMiniature: List<UnitCompositionMiniature>
    ) =
        withContext(Dispatchers.IO + Job()) {
            unitCompositionMiniatureDao.insert(unitCompositionMiniature)
        }
}