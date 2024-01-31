package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.dao.RuleContainerComponentDao
import com.aredruss.warmaster.domain.database.dao.RuleContainerDao
import com.aredruss.warmaster.domain.database.dao.RuleSectionDao
import com.aredruss.warmaster.domain.database.dao.StrategemDao

class RuleRepository(
    private val ruleSectionDao: RuleSectionDao,
    private val ruleContainerDao: RuleContainerDao,
    private val ruleContainerComponentDao: RuleContainerComponentDao,
    private val strategemDao: StrategemDao
) {
    suspend fun getRuleSections(pubId: String) = ruleSectionDao.getItemsById(pubId)
    suspend fun getChildSections(pubId: String) = ruleSectionDao.getChildItemsById(pubId)

    suspend fun getRuleContainers(sectionId: String) = ruleContainerDao
        .getItemsById(sectionId)
        .map {
            it.childContainerComponents = ruleContainerComponentDao.getItemsByRuleContainerId(it.id)
            val stratagem =
                it.stratagemId?.let { stratagemId -> strategemDao.getItemId(stratagemId) }
            it.childStrategem = stratagem?.copy(name = "Change: ${stratagem.name}")
            it
        }
}