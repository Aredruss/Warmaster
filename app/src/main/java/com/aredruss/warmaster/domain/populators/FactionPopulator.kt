package com.aredruss.warmaster.domain.populators

import com.aredruss.warmaster.domain.database.dao.DatasheetFactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.model.DatasheetFactionKeyword
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class FactionPopulator(
    private val factionKeywordDao: FactionKeywordDao,
    private val datasheetFactionKeywordDao: DatasheetFactionKeywordDao
) {
    suspend fun insertFactionKeyword(data: List<FactionKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            factionKeywordDao.insert(data)
        }

    suspend fun insertDatasheetFactionKeyword(data: List<DatasheetFactionKeyword>) =
        withContext(Dispatchers.IO + Job()) {
            datasheetFactionKeywordDao.insert(data)
        }
}