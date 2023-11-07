package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.model.Datasheet
import timber.log.Timber

class DatasheetRepository(
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao,
    private val datasheetDao: DatasheetDao,
    private val favoriteUnitRepository: FavoriteUnitRepository
) {
    suspend fun getDatasheetsForFaction(
        factionId: String,
        isSubFaction: Boolean
    ): Map<Boolean, List<Datasheet>> {
        val datasheetsIds = datasheetFactionKeywordDao
            .getAllDatasheetsByFaction(factionId = factionId)

        return datasheetDao.getItemsByIds(datasheetsIds).filter {
            if (!isSubFaction) {
                datasheetFactionKeywordDao.getDatasheetFactionCount(it.id) == 1
            } else {
                true
            }
        }.groupBy {
            it.isCombatPatrol
        }
    }

    suspend fun getFavoriteUnitsForFaction(
        factionId: String,
        isSubFaction: Boolean
    ): List<Datasheet> {
        val units = getDatasheetsForFaction(factionId, isSubFaction).values.flatten()
        val favoriteUnits = favoriteUnitRepository.getFavoriteUnitIdsByFactionId(factionId)
        return units.filter { datasheet ->
            datasheet.id in favoriteUnits
        }
    }

    suspend fun getAllFavoriteUnits(
    ): List<Pair<String, Datasheet>> {
        val favoriteUnits = favoriteUnitRepository.selectAllFavoriteIds()
        val favoriteIds = favoriteUnits.map { it.datasheetId }

        val datasheets = datasheetDao.getItemsByIds(favoriteIds)

         return datasheets.map { datasheet ->
             (favoriteUnits.firstOrNull { favoriteInfo ->
                 datasheet.id == favoriteInfo.datasheetId
             }?.factionId ?: "") to datasheet
        }
    }
}