package com.aredruss.warmaster.ui.datasheets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import com.aredruss.warmaster.data.model.Datasheet
import com.aredruss.warmaster.data.model.Faction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DataSheetViewModel(
    private val infoRepository: InfoRepository
) : ViewModel() {

    var datasheetList: List<Datasheet> by mutableStateOf(emptyList()); private set

    fun getDataByFaction(factionId: String) = viewModelScope.launch {
        infoRepository.ruleInfoStatListener.onEach {
            val idList = it?.data?.datasheetFactionKeywords?.filter { keyword ->
                keyword.factionKeywordId == factionId
            }?.map { entity ->
                entity.datasheetId
            }

            idList?.let { ids ->
                val data = it.data.datasheets.filter { sheet -> sheet.id in idList }
                datasheetList = data
            }

        }.launchIn(this)
    }
}