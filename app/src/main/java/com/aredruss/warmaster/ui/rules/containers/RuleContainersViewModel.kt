package com.aredruss.warmaster.ui.rules.containers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.RuleRepository
import com.aredruss.warmaster.domain.database.model.RuleContainer
import com.aredruss.warmaster.domain.database.model.RuleSection
import kotlinx.coroutines.launch

class RuleContainersViewModel(
    private val sectionName: String,
    private val sectionId: String,
    private val ruleRepository: RuleRepository,
) : ViewModel() {

    var publicationNameState: String by mutableStateOf(""); private set
    var publicationIdState: String by mutableStateOf(""); private set
    var publicationImageState: String by mutableStateOf(""); private set

    var containers: List<RuleContainer> by mutableStateOf(emptyList()); private set
    var subSections: List<RuleSection> by mutableStateOf(emptyList()); private set

    var loadingState: Boolean by mutableStateOf(true); private set


    init {
        loadingState = true
        publicationNameState = sectionName
        publicationIdState = sectionId

        viewModelScope.launch {
            containers = ruleRepository.getRuleContainers(sectionId)
            subSections = ruleRepository.getChildSections(sectionId)
            loadingState = false
        }
    }

}