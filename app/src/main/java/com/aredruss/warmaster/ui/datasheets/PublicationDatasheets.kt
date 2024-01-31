package com.aredruss.warmaster.ui.datasheets

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.InfoMessage
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PublicationDatasheets(
    pubName: String,
    publicationId: String,
    pubImage: String,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<PublicationDatasheetsViewModel> {
        parametersOf(pubName, publicationId, pubImage)
    }

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = viewModel.publicationNameState,
                additionalActionIcon = R.drawable.ic_search,
                enableAdditionalAction = true,
                additionalAction = {
                    navigator.navigate(
                        SearchScreenDestination(publicationId = viewModel.publicationIdState)
                    )
                },
                navigationAction = { navigator.popBackStack() })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                Crossfade(
                    targetState = viewModel.loadingState,
                    label = "",
                    modifier = Modifier.fillMaxSize(),
                ) { isLoading ->
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(size = 40.dp))
                        }
                    } else {
                        if (viewModel.datasheetList.isNotEmpty()) {
                            LazyColumn {
                                items(viewModel.datasheetList) { data ->
                                    DatasheetItem(datasheet = data) { id ->
                                        navigator.navigate(
                                            UnitPageDestination(id)
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
                }
            }
        })
}