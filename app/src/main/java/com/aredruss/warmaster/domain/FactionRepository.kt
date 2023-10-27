package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FactionRepository(
    private val factionKeywordDao: FactionKeywordDao
) {
    suspend fun getMainFactions() =
        factionKeywordDao.getFactions().filter { it.parentFactionKeywordId == null }

    suspend fun getSubFactions(parentId: String) = factionKeywordDao.getSubFactions(parentId)

    suspend fun checkSubFactionsExist(parentId: String) = withContext(Dispatchers.IO) {
        return@withContext factionKeywordDao.checkSubFactionsExist(parentId)
    }
}