package com.aredruss.warmaster.ui.factions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.database.model.Publication
import com.aredruss.warmaster.util.Event
import kotlinx.coroutines.launch

class FactionListViewModel(
    private val factionRepository: FactionRepository
) : ViewModel() {
    var factionKeywordList: List<FactionKeyword> by mutableStateOf(emptyList()); private set
    var navigateState: Event<NavigateFromFactionsState>? by mutableStateOf(null); private set

    var loadingState: Boolean by mutableStateOf(true); private set

    var corePublications: List<Publication> by mutableStateOf(emptyList()); private set

    init {
        loadingState = true
        viewModelScope.launch {
            factionKeywordList = factionRepository.getFactions()
            corePublications = factionRepository.getCorePublications()
            loadingState = false
        }
    }

    fun checkIfNeedSubFactions(
        factionId: String,
        name: String,
        image: String
    ) = viewModelScope.launch {
        navigateState = Event(
            if (factionRepository.checkSubFactionsExist(factionId)) {
                NavigateFromFactionsState.NavigateSubFactions(factionId)
            } else {
                NavigateFromFactionsState.NavigateDatasheets(factionId, name, image)
            }
        )
    }
}

sealed class NavigateFromFactionsState {
    class NavigateDatasheets(val id: String, val name: String, val image: String) :
        NavigateFromFactionsState()

    class NavigateSubFactions(val id: String) : NavigateFromFactionsState()
}