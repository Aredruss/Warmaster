package com.aredruss.warmaster.ui.subfaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import com.aredruss.warmaster.data.model.Faction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SubfactionViewModel(
    private val infoRepository: InfoRepository
) : ViewModel() {
    var factionList: List<Faction> by mutableStateOf(emptyList()); private set

    fun getSubfactionByFactionId(factionId: String) = viewModelScope.also { scope ->
        infoRepository.ruleInfoStatListener.onEach {
            factionList =
                it?.data?.factions?.filter { item ->
                    item.parentFactionKeywordId == factionId || item.id == factionId
                } ?: emptyList()
        }.launchIn(scope)
    }
}
