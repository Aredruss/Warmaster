package com.aredruss.warmaster.ui.factions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.about.AboutWarmaster
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.destinations.AboutWarmasterDestination
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.destinations.SubfactionListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun FactionList(navigator: DestinationsNavigator) {

    val viewModel = getViewModel<FactionListViewModel>()

    Column {
        CenteredTopBar(
            title = stringResource(id = R.string.factions),
            additionalAction = {
                navigator.navigate(AboutWarmasterDestination)
            }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.factionList) {
                FactionItem(faction = it) { _, id ->
                    navigator.navigate(SubfactionListDestination(id))
                }
            }
        }
    }
}