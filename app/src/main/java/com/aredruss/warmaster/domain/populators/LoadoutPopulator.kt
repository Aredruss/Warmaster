package com.aredruss.warmaster.domain.populators

import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceSetDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceWargearItemDao
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.LoadoutChoice
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceSet
import com.aredruss.warmaster.domain.database.model.LoadoutChoiceWargearItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class LoadoutPopulator(
    private val loadoutChoiceDao: LoadoutChoiceDao,
    private val loadoutChoiceSetDao: LoadoutChoiceSetDao,
    private val loadoutChoiceWargearItemDao: LoadoutChoiceWargearItemDao
) {
    suspend fun insertLoadoutChoice(data: List<LoadoutChoice>) =
        withContext(Dispatchers.IO + Job()) {
            loadoutChoiceDao.insert(data)
        }

    suspend fun insertLoadoutChoiceSet(data: List<LoadoutChoiceSet>) =
        withContext(Dispatchers.IO + Job()) {
            loadoutChoiceSetDao.insert(data)
        }

    suspend fun insertLoadoutChoiceWargearItem(data: List<LoadoutChoiceWargearItem>) =
        withContext(Dispatchers.IO + Job()) {
            loadoutChoiceWargearItemDao.insert(data)
        }
}