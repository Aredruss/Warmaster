package com.aredruss.warmaster.ui.factions

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.destinations.GameMenuDestination
import com.aredruss.warmaster.ui.destinations.SavedDatasheetsDestination
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.aredruss.warmaster.ui.destinations.SubFactionListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FactionList(navigator: DestinationsNavigator) {

    val viewModel = getViewModel<FactionListViewModel>()

    viewModel.navigateState?.consume()?.let { state ->
        when (state) {
            is NavigateFromFactionsState.NavigateSubFactions -> navigator.navigate(
                SubFactionListDestination(state.id)
            )

            is NavigateFromFactionsState.NavigateDatasheets -> navigator.navigate(
                GameMenuDestination(
                    factionId = state.id,
                    factionName = state.name,
                    factionImage = state.image,
                    isSubFaction = false
                )
            )
        }
    }

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(id = R.string.factions),
                enableAdditionalAction = true,
                additionalActionIcon = R.drawable.ic_search,
                additionalAction = {
                    navigator.navigate(
                        SearchScreenDestination(
                            factionId = "",
                            isSubFaction = false
                        )
                    )
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                ClickableTextLine(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.favorite_units)
                                ) {
                                    navigator.navigate(SavedDatasheetsDestination)
                                }
                            }
                            items(viewModel.factionKeywordList) {
                                FactionItem(factionKeyword = it) { name, id, image, _ ->
                                    viewModel.checkIfNeedSubFactions(
                                        factionId = id,
                                        name = name,
                                        image = image
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    )
}