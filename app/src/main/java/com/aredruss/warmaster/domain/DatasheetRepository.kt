package com.aredruss.warmaster.domain

import android.util.Log
import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.PublicationDao
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

class DatasheetRepository(
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao,
    private val datasheetDao: DatasheetDao,
    private val favoriteUnitRepository: FavoriteUnitRepository,
    private val publicationDao: PublicationDao
) {
    suspend fun getDatasheetsForFaction(
        factionId: String,
        isSubFaction: Boolean
    ): List<Datasheet> {
        val datasheetsIds = datasheetFactionKeywordDao
            .getAllDatasheetsByFaction(factionId = factionId)

        val datasheets = datasheetDao.getItemsByIds(datasheetsIds).filter {
            if (!isSubFaction) {
                datasheetFactionKeywordDao.getDatasheetFactionCount(it.id) == 1
            } else {
                true
            }
        }.sortedBy { it.displayOrder }

        return datasheets
    }

    suspend fun getFavoriteUnitsForFaction(
        factionId: String,
        isSubFaction: Boolean
    ): List<Datasheet> {
        val units = getDatasheetsForFaction(factionId, isSubFaction)
        val favoriteUnits = favoriteUnitRepository.getFavoriteUnitIdsByFactionId(factionId)
        return units.filter { datasheet ->
            datasheet.id in favoriteUnits
        }.map {
            getPublicationName(it)
        }
    }

    suspend fun getAllFavoriteUnits(
    ): List<Datasheet> {
        val favoriteUnits = favoriteUnitRepository.selectAllFavorites()
        val favoriteIds = favoriteUnits.map { it.datasheetId }
        return datasheetDao.getItemsByIds(favoriteIds).map {
            getPublicationName(it)
        }
    }

    suspend fun getDatasheetsByQuery(query: String) = datasheetDao
        .getItemsByName(query)
        .distinctUntilChanged()

    suspend fun getDatasheetsPublication(id: String) = withContext(Dispatchers.IO) {
        return@withContext datasheetDao.getDataByPubId(id)
    }

    suspend fun getDatasheetsByFaction(
        query: String,
        factionId: String,
        isSubFaction: Boolean
    ): Flow<List<Datasheet>> {
        val datasheetIdsByFaction = getDatasheetsForFaction(factionId, isSubFaction)
            .map {
                it.id
            }
        return getDatasheetsByQuery(query).map { list ->
            list.filter { it.id in datasheetIdsByFaction }.map {
                getPublicationName(it)
            }
        }
    }

    suspend fun getDatasheetsByPublication(
        query: String,
        publicationId: String
    ): Flow<List<Datasheet>> {
        val datasheetIdsByPublication = getDatasheetsPublication(publicationId)
            .map {
                it.id
            }
        return getDatasheetsByQuery(query).map { list ->
            list.filter { it.id in datasheetIdsByPublication }.map {
                getPublicationName(it)
            }
        }
    }

    private suspend fun getPublicationName(item: Datasheet): Datasheet {
        item.publicationId?.let {
            item.publicationName = publicationDao.getPublicationNameById(it)
        }
        return item
    }
}