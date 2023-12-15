package com.aredruss.warmaster.ui.datasheets.index

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DetachmentInfoRepository
import com.aredruss.warmaster.domain.database.model.Detachment
import kotlinx.coroutines.launch
import timber.log.Timber

class IndexScreenViewModel(
    private val factionName: String,
    private val factionId: String,
    private val isSubFaction: Boolean,
    private val factionImage: String,
    private val publicationId: String,
    private val detachmentInfoRepository: DetachmentInfoRepository
) : ViewModel() {

    var factionNameState: String by mutableStateOf(""); private set
    var factionIdState: String by mutableStateOf(""); private set
    var isSubFactionState: Boolean by mutableStateOf(false); private set
    var factionImageState: String by mutableStateOf(""); private set
    var publicationIdState: String by mutableStateOf(""); private set

    var detachments: List<Detachment> by mutableStateOf(emptyList()); private set

    init {
        factionNameState = factionName
        factionIdState = factionId
        isSubFactionState = isSubFaction
        factionImageState = factionImage
        publicationIdState = publicationId

        viewModelScope.launch {
            detachments = detachmentInfoRepository.getDetachmentsForFactionId(factionId = factionId, isSubFaction)
        }
    }
}