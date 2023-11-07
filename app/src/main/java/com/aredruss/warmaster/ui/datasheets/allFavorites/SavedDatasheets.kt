package com.aredruss.warmaster.ui.datasheets.allFavorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.InfoMessage
import com.aredruss.warmaster.ui.datasheets.DatasheetItem
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SavedDatasheets(
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<SavedDatasheetsViewModel>()

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(R.string.favorite_units),
                navigationAction = { navigator.popBackStack() })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                if (viewModel.datasheetList.isNotEmpty()) {
                    LazyColumn {
                        items(viewModel.datasheetList) { data ->
                            DatasheetItem(datasheet = data.second) { id ->
                                navigator.navigate(
                                    UnitPageDestination(
                                        id,
                                        data.first
                                    )
                                )
                            }
                        }
                    }
                } else {
                    InfoMessage(
                        text = stringResource(R.string.no_sheets_av),
                        action = {}
                    )
                }
            }
        })
}