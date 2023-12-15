package com.aredruss.warmaster.ui.datasheets.patrol

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PatrolViewModel (
    private val factionName: String,
    private val publicationId: String,
    private val factionImage: String,
) : ViewModel() {

    var factionNameState: String by mutableStateOf(""); private set
    var publicationIdState: String by mutableStateOf(""); private set
    var factionImageState: String by mutableStateOf(""); private set

    init {
        factionNameState = factionName
        publicationIdState = publicationId
        factionImageState = factionImage
    }
}