package com.aredruss.warmaster.ui.factions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.util.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FactionListViewModel(
    private val factionRepository: FactionRepository
) : ViewModel() {
    var factionKeywordList: List<FactionKeyword> by mutableStateOf(emptyList()); private set
    var navigateState: Event<NavigateFromFactionsState>? by mutableStateOf(null); private set

    init {
        viewModelScope.launch {
            factionKeywordList = factionRepository.getMainFactions()
        }
    }

    fun checkIfNeedSubFactions(factionId: String, name: String) = viewModelScope.launch {
        navigateState = Event(
            if (factionRepository.checkSubFactionsExist(factionId)) {
                NavigateFromFactionsState.NavigateSubFactions(factionId)
            } else {
                NavigateFromFactionsState.NavigateDatasheets(factionId, name)
            }
        )
    }
}

sealed class NavigateFromFactionsState {
    class NavigateDatasheets(val id: String, val name: String) : NavigateFromFactionsState()
    class NavigateSubFactions(val id: String) : NavigateFromFactionsState()
}