package com.aredruss.warmaster.ui.datasheets.gameType

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class GameTypeViewModel(
    factionName: String,
    factionId: String,
    isSubFaction: Boolean,
    factionImage: String
) : ViewModel() {

    var factionNameState: String by mutableStateOf(""); private set
    var factionIdState: String by mutableStateOf(""); private set
    var isSubFactionState: Boolean by mutableStateOf(false); private set
    var factionImageState: String by mutableStateOf(""); private set

    init {
        factionNameState = factionName
        factionIdState = factionId
        isSubFactionState = isSubFaction
        factionImageState = factionImage
    }
}