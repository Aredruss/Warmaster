package com.aredruss.warmaster.ui.datasheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun DataSheetList(
    factionName: String,
    factionId: String,
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<DataSheetViewModel>()

    viewModel.getDataByFaction(factionId)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        CenteredTopBar(
            title = factionName,
            navigationAction = { navigator.popBackStack() })
        LazyColumn() {
            items(viewModel.datasheetList) {
                DatasheetItem(datasheet = it) { id ->
                    navigator.navigate(UnitPageDestination(id))
                }
            }
        }
    }


}