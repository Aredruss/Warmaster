package com.aredruss.warmaster.ui.datasheets.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.database.model.Datasheet
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel(
    private val factionId: String = "",
    private val isSubFaction: Boolean = false,
    private val datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set
    var loadingState: Boolean by mutableStateOf(false); private set

    fun clearList() {
        datasheetList = emptyList()
    }

    fun getData(query: String) {
        loadingState = true
        if (factionId.isNotEmpty()) {
            getDatasheetsByQueryAndFaction(query, isSubFaction, factionId)
        } else {
            getDatasheetsByQuery(query = query)
        }
    }

    private fun getDatasheetsByQuery(query: String) = viewModelScope.launch {
        datasheetRepository
            .getDatasheetsByQuery(query)
            .onEach {
                loadingState = false
                datasheetList = it
            }.launchIn(viewModelScope)
    }

    private fun getDatasheetsByQueryAndFaction(
        query: String,
        isSubFaction: Boolean,
        factionId: String
    ) = viewModelScope.launch {
        datasheetRepository
            .getDatasheetsByQueryWithFilter(query, factionId, isSubFaction)
            .onEach {
                loadingState = false
                datasheetList = it
            }.launchIn(viewModelScope)
    }
}
