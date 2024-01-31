package com.aredruss.warmaster.ui.datasheets.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.domain.database.model.Publication
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class GameMenuViewModel(
    private val factionName: String,
    private val factionId: String,
    private val factionImage: String,
    private val isSubFaction: Boolean,
    private val factionRepository: FactionRepository
) : ViewModel() {
    var factionNameState: String by mutableStateOf(""); private set
    var factionIdState: String by mutableStateOf(""); private set
    var isSubFactionState: Boolean by mutableStateOf(false); private set
    var factionImageState: String by mutableStateOf(""); private set
    var publications: List<Publication> by mutableStateOf(emptyList()); private set

    init {
        factionNameState = factionName
        factionIdState = factionId
        isSubFactionState = isSubFaction
        factionImageState = factionImage

        viewModelScope.launch {
            publications = factionRepository.getPublicationsByFaction(factionIdState)
        }
    }
}