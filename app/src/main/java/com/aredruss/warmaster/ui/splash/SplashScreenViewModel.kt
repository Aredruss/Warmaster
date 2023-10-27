package com.aredruss.warmaster.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.domain.InfoRepository

import com.aredruss.warmaster.util.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashScreenViewModel(
    private val infoRepository: InfoRepository
) : ViewModel() {

    var allowNavigateNext: Event<Boolean>? by mutableStateOf(null); private set

    init {
        loadDataIfNecessary()
    }

    private fun loadDataIfNecessary() = viewModelScope.launch {
        infoRepository.parseDataFile()
            .onSuccess {
                allowNavigateNext = Event(true)
            }.onFailure {
                Timber.e(it)
            }
    }

}