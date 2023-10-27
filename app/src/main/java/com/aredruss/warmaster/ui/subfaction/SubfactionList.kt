package com.aredruss.warmaster.ui.subfaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.factions.FactionItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun SubfactionList(
    factionId: String,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<SubFactionViewModel>()
    viewModel.getSubFactionByFactionId(factionId)

    Column {
        CenteredTopBar(
            title = stringResource(id = R.string.subfactions),
            navigationAction = {
                navigator.popBackStack()
            }
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.factionKeywordList) {
                FactionItem(factionKeyword = it) { name, id ->
                    navigator.navigate(DataSheetListDestination(name, id))
                }
            }
        }
    }
}