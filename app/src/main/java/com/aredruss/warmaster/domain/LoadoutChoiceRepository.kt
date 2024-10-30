package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceSetDao
import com.aredruss.warmaster.domain.database.dao.LoadoutChoiceWargearItemDao
import com.aredruss.warmaster.domain.database.dao.WargearItemDao
import com.aredruss.warmaster.domain.database.model.WargearItem

class LoadoutChoiceRepository(
    private val loadoutChoiceDao: LoadoutChoiceDao,
    private val loadoutChoiceSetDao: LoadoutChoiceSetDao,
    private val loadoutChoiceWargearItemDao: LoadoutChoiceWargearItemDao,
    private val wargearItemDao: WargearItemDao
) {
    suspend fun getCustomWargearByDatasheet(datasheetId: String): List<WargearItem> {
        val sets = loadoutChoiceSetDao.getItemsByIds(datasheetId)
        val choices = loadoutChoiceDao.getItemsByIds(sets)
        val bonds = loadoutChoiceWargearItemDao.getItemsByIds(choices)
        return wargearItemDao.getItemsByIds(bonds).filter { it.ruleText != null }
    }
}