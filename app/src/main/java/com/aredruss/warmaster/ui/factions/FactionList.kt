package com.aredruss.warmaster.ui.factions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun FactionList() {
    val viewModel = getViewModel<FactionListViewModel>()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.factionList) {
            FactionItem(faction = it)
        }
    }
}