package com.aredruss.warmaster.domain.util

import com.aredruss.warmaster.domain.FavoriteUnitRepository
import com.aredruss.warmaster.domain.database.WarmasterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber

class ClearUtil(
    private val database: WarmasterDatabase,
    private val favoriteUnitRepository: FavoriteUnitRepository
) {
    suspend fun purgeDatabase() = withContext(Dispatchers.IO + Job()){
        val favs = favoriteUnitRepository.selectAllFavorites()
        database.clearAllTables().apply {
            Timber.e("Purging the database")
        }
        favoriteUnitRepository.insertAll(favs).apply {
            Timber.e("Inserting favorites")
        }
    }
}