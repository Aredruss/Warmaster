package com.aredruss.warmaster.ui.factions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.destinations.AboutWarmasterDestination
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.destinations.SubfactionListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun FactionList(navigator: DestinationsNavigator) {

    val viewModel = getViewModel<FactionListViewModel>()

    viewModel.navigateState?.consume()?.let { state ->
        when (state) {
            is NavigateFromFactionsState.NavigateSubFactions -> navigator.navigate(
                SubfactionListDestination(state.id)
            )

            is NavigateFromFactionsState.NavigateDatasheets -> navigator.navigate(
                DataSheetListDestination(state.name, state.id)
            )
        }
    }

    Column {
        CenteredTopBar(
            title = stringResource(id = R.string.factions),
            additionalAction = {
                navigator.navigate(AboutWarmasterDestination)
            }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.factionKeywordList) {
                FactionItem(factionKeyword = it) { name, id ->
                    viewModel.checkIfNeedSubFactions(factionId = id, name = name)
                }
            }
        }
    }
}