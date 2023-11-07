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

    suspend fun selectAllFavoriteIds() = favoriteUnitDao.getAllItemIds()

    suspend fun deleteFavorite(datasheetId: String) =
        favoriteUnitDao.deleteUnit(datasheetId = datasheetId)

    suspend fun isFavorite(datasheetId: String) =
        favoriteUnitDao.checkIfFavorite(datasheetId)

    suspend fun getFavoriteUnitIdsByFactionId(factionId: String) =
        favoriteUnitDao.getIdsByFactionId(factionId)
}