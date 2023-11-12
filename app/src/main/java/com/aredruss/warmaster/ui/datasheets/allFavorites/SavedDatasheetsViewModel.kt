package com.aredruss.warmaster.ui.datasheets.allFavorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.launch

class SavedDatasheetsViewModel(
    datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set

    init {
        viewModelScope.launch {
            datasheetList = datasheetRepository.getAllFavoriteUnits()
        }
    }
}