package com.aredruss.warmaster.ui.rules.sections

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.RuleRepository
import com.aredruss.warmaster.domain.database.model.RuleSection
import kotlinx.coroutines.launch

class RuleSectionListViewModel(
    private val pubName: String,
    private val publicationId: String,
    private val publicationImage: String,
    private val ruleRepository: RuleRepository,
) : ViewModel() {

    private var publicationIdState: String by mutableStateOf(""); private set

    var publicationNameState: String by mutableStateOf(""); private set

    var publicationImageState: String by mutableStateOf(""); private set

    var sections: List<RuleSection> by mutableStateOf(emptyList()); private set

    var loadingState: Boolean by mutableStateOf(true); private set

    init {
        publicationNameState = pubName
        publicationIdState = publicationId
        publicationImageState = publicationImage


        viewModelScope.launch {
            sections = ruleRepository.getRuleSections(publicationId)
            loadingState = false
        }
    }

}