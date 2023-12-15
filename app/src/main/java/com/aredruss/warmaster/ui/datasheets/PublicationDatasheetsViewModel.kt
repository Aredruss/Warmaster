package com.aredruss.warmaster.ui.datasheets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.launch

class PublicationDatasheetsViewModel(
    pubName: String,
    publicationId: String,
    pubImage: String,
    datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set
    var publicationNameState: String by mutableStateOf(""); private set
    var publicationIdState: String by mutableStateOf(""); private set
    var publicationImageState: String by mutableStateOf(""); private set
    var loadingState: Boolean by mutableStateOf(true); private set

    init {
        loadingState = true
        publicationNameState = pubName
        publicationImageState = pubImage
        publicationIdState = publicationId
        viewModelScope.launch {
            datasheetList = datasheetRepository.getDatasheetsPublication(publicationId)
            loadingState = false
        }
    }

}