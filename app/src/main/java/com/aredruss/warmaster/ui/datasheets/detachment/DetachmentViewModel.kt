package com.aredruss.warmaster.ui.datasheets.detachment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DetachmentInfoRepository
import com.aredruss.warmaster.domain.database.model.Detachment
import com.aredruss.warmaster.domain.database.model.DetachmentRule
import kotlinx.coroutines.launch

class DetachmentViewModel(
    detachmentId: String,
    detachmentName: String,
    detachmentInfoRepository: DetachmentInfoRepository
) : ViewModel() {

    private var detachmentIdState: String by mutableStateOf("")
    var detachmentNameState: String by mutableStateOf(""); private set

    var detachmentState: Detachment? by mutableStateOf(null); private set
    var detachmentRuleState: List<DetachmentRule> by mutableStateOf(emptyList()); private set

    init {
        detachmentIdState = detachmentId
        detachmentNameState = detachmentName
        viewModelScope.launch {
            detachmentState = detachmentInfoRepository.getDetachmentInfoById(detachmentId)
            detachmentRuleState = detachmentInfoRepository.getDetachmentRules(detachmentId)
        }

    }
}