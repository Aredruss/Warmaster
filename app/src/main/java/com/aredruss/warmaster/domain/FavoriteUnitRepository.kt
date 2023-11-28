package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.FavoriteUnitDao
import com.aredruss.warmaster.domain.database.index.FavoriteUnit

class FavoriteUnitRepository(
    private val favoriteUnitDao: FavoriteUnitDao
) {

    suspend fun insertFavorite(
        datasheetId: String,
        factionId: String
    ) =
        favoriteUnitDao.insert(
            FavoriteUnit(
                datasheetId = datasheetId,
                factionId = factionId
            )
        )

    suspend fun selectAllFavorites() = favoriteUnitDao.getAllItems()

    suspend fun insertAll(items: List<FavoriteUnit>) = favoriteUnitDao.insertAll(items)

    suspend fun deleteFavorite(datasheetId: String) =
        favoriteUnitDao.deleteUnit(datasheetId = datasheetId)

    suspend fun isFavorite(datasheetId: String) =
        favoriteUnitDao.checkIfFavorite(datasheetId)

    suspend fun getFavoriteUnitIdsByFactionId(factionId: String) =
        favoriteUnitDao.getIdsByFactionId(factionId)
}