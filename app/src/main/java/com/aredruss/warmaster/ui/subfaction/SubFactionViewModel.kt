package com.aredruss.warmaster.ui.subfaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.domain.database.model.FactionKeyword
import kotlinx.coroutines.launch

class SubFactionViewModel(
    private val factionId: String,
    private val factionRepository: FactionRepository
) : ViewModel() {
    var factionKeywordList: List<FactionKeyword> by mutableStateOf(emptyList()); private set

    init {
        viewModelScope.launch {
            factionKeywordList = factionRepository.getSubFactions(factionId)
        }
    }

}
