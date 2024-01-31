package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.FactionKeywordDao
import com.aredruss.warmaster.domain.database.dao.PublicationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FactionRepository(
    private val factionKeywordDao: FactionKeywordDao,
    private val publicationDao: PublicationDao
) {
    suspend fun getFactions() = factionKeywordDao.getFactions()

    suspend fun getSubFactions(parentId: String) = factionKeywordDao.getSubFactions(parentId)

    suspend fun checkSubFactionsExist(parentId: String) = withContext(Dispatchers.IO) {
        return@withContext factionKeywordDao.checkSubFactionsExist(parentId)
    }

    suspend fun getPublicationsByFaction(id: String) = withContext(Dispatchers.IO) {
        return@withContext publicationDao.getByFactionKeywordId(id)
    }

    suspend fun getCorePublications() = withContext(Dispatchers.IO) {
        return@withContext publicationDao.getCorePublications()
    }
}