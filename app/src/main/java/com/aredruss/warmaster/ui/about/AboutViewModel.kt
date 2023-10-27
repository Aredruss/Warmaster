package com.aredruss.warmaster.ui.about

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aredruss.warmaster.domain.WarmasterPrefs

class AboutViewModel(
    prefs: WarmasterPrefs
) : ViewModel() {
    var aboutInfo: String? by mutableStateOf(null); private set

    init {
        aboutInfo = prefs.currentDataVersion.toString()
    }
}