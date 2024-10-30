package com.aredruss.warmaster.ui.factions

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.destinations.RulelistDestination
import com.aredruss.warmaster.ui.destinations.SavedDatasheetsDestination
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FactionList(navigator: DestinationsNavigator) {

    val viewModel = getViewModel<FactionListViewModel>()

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(id = R.string.factions),
                enableAdditionalAction = true,
                additionalActionIcon = R.drawable.ic_search,
                additionalAction = {
                    navigator.navigate(
                        SearchScreenDestination(
                            publicationId = ""
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
                            items(viewModel.corePublications) {
                                ClickableTextLine(
                                    modifier = Modifier,
                                    text = it.name
                                ) {
                                    navigator.navigate(
                                        RulelistDestination(
                                            publicationId = it.id,
                                            pubName = it.name,
                                            publicationImage = it.factionBackgroundImage
                                        )
                                    )
                                }
                            }
                            item {
                                ClickableTextLine(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.favorite_units)
                                ) {
                                    navigator.navigate(SavedDatasheetsDestination)
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(height = 10.dp))
                            }
                            item {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp),
                                    text = stringResource(id = R.string.faction_data),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(height = 5.dp))
                            }
                            items(viewModel.factionKeywordList) {
                                FactionItem(factionKeyword = it) { name, id, image, isSub ->
                                    navigator.navigate(
                                        com.aredruss.warmaster.ui.destinations.GameMenuDestination(
                                            factionName = name,
                                            factionId = id,
                                            factionImage = image,
                                            isSubFaction = isSub
                                        )
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