package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.DatasheetDao
import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.model.Datasheet

class DatasheetRepository(
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao,
    private val datasheetDao: DatasheetDao,
) {
    suspend fun getDatasheetsForFaction(factionId: String): Map<Boolean, List<Datasheet>> {
        val datasheets = datasheetFactionKeywordDao
            .getAllDatasheetsByFaction(factionId = factionId)
            .map {
                it.datasheetId
            }
        return datasheetDao.getItemsByIds(datasheets).groupBy {
            it.isCombatPatrol
        }
    }
}