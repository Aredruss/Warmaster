package com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DetachmentInfoRepository
import com.aredruss.warmaster.domain.database.model.Enhancement
import com.aredruss.warmaster.domain.database.model.SecondaryObjective
import com.aredruss.warmaster.domain.database.model.Strategem
import kotlinx.coroutines.launch

class MinorRulesViewModel(
    private val parentId: String,
    private val shouldUseDetachments: Boolean = true,
    ruleScreenType: RuleScreenType = RuleScreenType.ENHANCE,
    private val detachmentInfoRepository: DetachmentInfoRepository
) : ViewModel() {

    companion object {
        enum class RuleScreenType(val id: Int) {
            ENHANCE(id = 0),
            STRATEGEM(id = 1),
            SECONDARY(id = 2)
        }
    }

    var parentIdState: String by mutableStateOf("")
    var shouldUseDetachmentsState: Boolean by mutableStateOf(false); private set


    var ruleScreenTypeState: RuleScreenType? by mutableStateOf(null); private set

    var enhancements: List<Enhancement> by mutableStateOf(emptyList()); private set
    var strategems: List<Strategem> by mutableStateOf(emptyList()); private set
    var secondaryObjectives: List<SecondaryObjective> by mutableStateOf(emptyList()); private set


    init {
        parentIdState = parentId
        shouldUseDetachmentsState = shouldUseDetachments
        ruleScreenTypeState = ruleScreenType
        when (ruleScreenTypeState) {
            RuleScreenType.ENHANCE -> {
                getEnhancements()
            }

            RuleScreenType.STRATEGEM -> {
                getStrategems()
            }

            else -> {
                getSecondaries()
            }
        }

    }

    private fun getStrategems() = viewModelScope.launch {
        strategems = detachmentInfoRepository.getStrategems(
            parentIdState,
            shouldUseDetachmentsState
        )
    }

    private fun getEnhancements() = viewModelScope.launch {
        enhancements = detachmentInfoRepository.getEnhancements(
            parentIdState,
            shouldUseDetachmentsState
        )
    }

    private fun getSecondaries() = viewModelScope.launch {
        secondaryObjectives = detachmentInfoRepository.getSecondaryObjectives(parentIdState)
    }
}