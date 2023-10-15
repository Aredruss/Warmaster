package com.aredruss.warmaster.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aredruss.warmaster.data.InfoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val infoRepository: InfoRepository) : ViewModel() {

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
    }

}