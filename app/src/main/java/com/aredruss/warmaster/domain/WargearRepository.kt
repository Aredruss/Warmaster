package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.WargearAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionGroupDao
import com.aredruss.warmaster.domain.database.dao.WargearRuleDao
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import timber.log.Timber

class WargearRepository(
    private val wargearItemProfileAbilityDao: WargearItemProfileAbilityDao,
    private val wargearAbilityDao: WargearAbilityDao,
    private val wargearItemProfileDao: WargearItemProfileDao,
    private val wargearItemDao: WargearItemDao,
    private val wargearOptionGroupDao: WargearOptionGroupDao,
    private val wargearOptionDao: WargearOptionDao,
    private val wargearRuleDao: WargearRuleDao
) {
    suspend fun getWargearRules(datasheetId: String) =
        wargearRuleDao.getItemsById(datasheetId)

    suspend fun getDatasheetWargear(datasheetId: String): Map<String, List<WargearItemProfile>> {
        val groupOptions = getWargearOptionGroups(datasheetId)

        val options = wargearOptionDao.getItemsByGroups(groupOptions)

        val items = wargearItemDao.getItemsByIds(options)

        return wargearItemProfileDao.getItemsById(options).map {
            val item = items.find { item -> item.id == it.wargearItemId }

            it.wargearItemRule = item?.ruleText
            it.wargearItemName = if (it.name != item?.name) "${item?.name} - ${it.name}" else it.name

            val wargearAbilitiesIds = wargearItemProfileAbilityDao.getItemsById(it.id)
            it.wargearAbilities = wargearAbilityDao.getItemsById(wargearAbilitiesIds)

            it
        }.groupBy {
            it.type
        }
    }

    private suspend fun getWargearOptionGroups(datasheetId: String) =
        wargearOptionGroupDao.getItemsByDatasheet(datasheetId)

}