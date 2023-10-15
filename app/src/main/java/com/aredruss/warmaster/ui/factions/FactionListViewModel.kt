package com.aredruss.warmaster.ui.factions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import com.aredruss.warmaster.data.model.Faction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class FactionListViewModel(
    private val infoRepository: InfoRepository
) : ViewModel() {

    var factionList: List<Faction> by mutableStateOf(emptyList()); private set

    init {
        viewModelScope.launch {
            infoRepository.readStats()
        }
        viewModelScope.also { scope ->
            infoRepository.ruleInfoStatListener.onEach {
                factionList =
                    it?.data?.factions?.filter { item ->
                        item.parentFactionKeywordId == null
                    } ?: emptyList()
            }.launchIn(scope)
        }
    }
}