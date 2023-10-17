package com.aredruss.warmaster.ui.about

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import com.aredruss.warmaster.data.model.MetaData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AboutViewModel(
    private val infoRepository: InfoRepository
) : ViewModel() {
    var aboutInfo: MetaData? by mutableStateOf(null); private set

    init {
        viewModelScope.also { scope ->
            infoRepository.ruleInfoStatListener.onEach {
                aboutInfo = it?.metaData
            }.launchIn(scope)
        }
    }
}