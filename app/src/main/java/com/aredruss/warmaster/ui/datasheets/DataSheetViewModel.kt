package com.aredruss.warmaster.ui.datasheets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.launch

class DataSheetViewModel(
    private val factionId: String,
    private val factionName: String,
    private val isSubFaction: Boolean,
    private val isPatrol: Boolean,
    private val isFavorites: Boolean,
    private val datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set
    var factionNameState: String by mutableStateOf(""); private set
    var factionIdState: String by mutableStateOf(""); private set
    var loadingState: Boolean by mutableStateOf(true); private set

    init {
        loadingState = true
        factionNameState = factionName
        factionIdState = factionId
        viewModelScope.launch {
            datasheetList = if (isFavorites) {
                datasheetRepository.getFavoriteUnitsForFaction(
                    factionId = factionId,
                    isSubFaction = isSubFaction
                )

            } else {
                val items = datasheetRepository.getDatasheetsForFaction(
                    factionId = factionId,
                    isSubFaction = isSubFaction
                )
                items[isPatrol] ?: emptyList()
            }
            loadingState = false
        }
    }
}
