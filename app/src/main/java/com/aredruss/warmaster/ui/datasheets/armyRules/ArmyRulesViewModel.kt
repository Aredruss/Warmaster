package com.aredruss.warmaster.ui.datasheets.armyRules

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DetachmentInfoRepository
import com.aredruss.warmaster.domain.database.model.ArmyRule
import kotlinx.coroutines.launch

class ArmyRulesViewModel(
    pubName: String,
    publicationId: String,
    publicationImage: String,
    detachmentInfoRepository: DetachmentInfoRepository
) : ViewModel() {

    var publicationNameState: String by mutableStateOf(""); private set
    var publicationIdState: String by mutableStateOf(""); private set
    var publicationImageState: String by mutableStateOf(""); private set

    var rules: List<ArmyRule> by mutableStateOf(emptyList());  private set

    var loadingState: Boolean by mutableStateOf(true); private set

    init {
        publicationNameState = pubName
        publicationIdState = publicationId
        publicationImageState = publicationImage

        loadingState = true
        viewModelScope.launch {
            loadingState = false
            rules = detachmentInfoRepository.getArmyRules(publicationId)
        }
    }
}