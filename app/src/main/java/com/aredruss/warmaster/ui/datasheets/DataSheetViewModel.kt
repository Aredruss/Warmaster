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
    private val datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: DatasheetLists by mutableStateOf(DatasheetLists()); private set
    var factionNameState: String by mutableStateOf(""); private set

    init {
        factionNameState = factionName
        viewModelScope.launch {
            val items = datasheetRepository.getDatasheetsForFaction(factionId = factionId)
            datasheetList = DatasheetLists(
                items[false] ?: emptyList(),
                items[true] ?: emptyList()
            )
        }
    }

}

data class DatasheetLists(
    val normalList: List<Datasheet> = emptyList(),
    val patrolList: List<Datasheet> = emptyList()
)