package com.aredruss.warmaster.ui.army

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ArmyListViewModel: ViewModel() {
    var loadingState: Boolean by mutableStateOf(false); private set
}