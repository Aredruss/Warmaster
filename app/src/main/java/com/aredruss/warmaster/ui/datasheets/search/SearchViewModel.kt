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

class SearchViewModel(
    private val publicationId: String = "",
    private val factionId: String = "",
    private val useFactionSearch: Boolean = false,
    private val isSubfaction: Boolean = false,
    private val datasheetRepository: DatasheetRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set
    var loadingState: Boolean by mutableStateOf(false); private set

    fun clearList() {
        datasheetList = emptyList()
    }

    fun getData(query: String) {
        loadingState = true
        if (publicationId.isNotBlank() || factionId.isNotBlank()) {
            getDatasheetsByQueryAndPublication(query)
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

    private fun getDatasheetsByQueryAndPublication(
        query: String
    ) = viewModelScope.launch {
        if (useFactionSearch) {
            datasheetRepository.getDatasheetsByFaction(query, factionId, isSubfaction)
        } else {
            datasheetRepository
                .getDatasheetsByPublication(query, publicationId)
        }.onEach {
            loadingState = false
            datasheetList = it
        }.launchIn(viewModelScope)
    }
}
