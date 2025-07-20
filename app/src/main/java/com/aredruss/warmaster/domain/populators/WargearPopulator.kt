package com.aredruss.warmaster.domain.populators

import com.aredruss.warmaster.domain.database.dao.RuleContainerComponentDao
import com.aredruss.warmaster.domain.database.dao.WargearAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileAbilityDao
import com.aredruss.warmaster.domain.database.dao.WargearItemProfileDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionDao
import com.aredruss.warmaster.domain.database.dao.WargearOptionGroupDao
import com.aredruss.warmaster.domain.database.dao.WargearRuleDao
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.WargearAbility
import com.aredruss.warmaster.domain.database.model.WargearItem
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import com.aredruss.warmaster.domain.database.model.WargearItemProfileAbility
import com.aredruss.warmaster.domain.database.model.WargearOption
import com.aredruss.warmaster.domain.database.model.WargearOptionGroup
import com.aredruss.warmaster.domain.database.model.WargearRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class WargearPopulator(
    private val wargearItemDao: WargearItemDao,
    private val wargearItemProfileAbilityDao: WargearItemProfileAbilityDao,
    private val wargearItemProfileDao: WargearItemProfileDao,
    private val wargearAbilityDao: WargearAbilityDao,
    private val wargearOptionGroupDao: WargearOptionGroupDao,
    private val wargearOptionDao: WargearOptionDao,
    private val wargearRuleDao: WargearRuleDao,
    private val ruleContainerComponentDao: RuleContainerComponentDao,
) {
    suspend fun insertWargearItem(data: List<WargearItem>) =
        withContext(Dispatchers.IO + Job()) {
            wargearItemDao.insert(data)
        }

    suspend fun insertWargearItemProfile(data: List<WargearItemProfile>) =
        withContext(Dispatchers.IO + Job()) {
            wargearItemProfileDao.insert(data)
        }

    suspend fun insertWargearItemProfileAbility(data: List<WargearItemProfileAbility>) =
        withContext(Dispatchers.IO + Job()) {
            wargearItemProfileAbilityDao.insert(data)
        }

    suspend fun insertWargearAbility(data: List<WargearAbility>) =
        withContext(Dispatchers.IO + Job()) {
            wargearAbilityDao.insert(data)
        }

    suspend fun insertWargearOptionGroup(data: List<WargearOptionGroup>) =
        withContext(Dispatchers.IO + Job()) {
            wargearOptionGroupDao.insert(data)
        }

    suspend fun insertWargearOption(data: List<WargearOption>) =
        withContext(Dispatchers.IO + Job()) {
            wargearOptionDao.insert(data)
        }

    suspend fun insertWargearRule(data: List<WargearRule>) =
        withContext(Dispatchers.IO + Job()) {
            wargearRuleDao.insert(data)
        }

    suspend fun insertRuleContainerComponent(data: List<RuleContainerComponent>) =
        withContext(Dispatchers.IO + Job()) {
            ruleContainerComponentDao.insert(data.map {
                it.copy(textContent = it.textContent ?: "")
            })
        }
}