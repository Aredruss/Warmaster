package com.aredruss.warmaster.ui.datasheets.menu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.destinations.IndexScreenDestination
import com.aredruss.warmaster.ui.destinations.PatrolMenuDestination
import com.aredruss.warmaster.ui.destinations.PublicationDatasheetsDestination
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun GameMenu(
    factionName: String,
    factionId: String,
    factionImage: String,
    isSubFaction: Boolean,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<GameMenuViewModel> {
        parametersOf(factionName, factionId, isSubFaction, factionImage)
    }


    Scaffold(topBar = {
        CenteredTopBar(title = viewModel.factionNameState,
            navigationAction = {
                navigator.popBackStack()
            },
            additionalActionIcon = R.drawable.ic_search,
            enableAdditionalAction = true,
            additionalAction = {
                navigator.navigate(
                    SearchScreenDestination(
                        viewModel.factionIdState, viewModel.isSubFactionState
                    )
                )
            })
    }, content = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 150.dp),
                    model = viewModel.factionImageState,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    alignment = Alignment.Center,
                    filterQuality = FilterQuality.High
                )
            }
            item {
                Spacer(modifier = Modifier.height(height = 10.dp))
            }
            items(viewModel.publications) { publication ->
                val name = if (publication.combatPatrolName != null) {
                    "Combat Patrol: ${publication.combatPatrolName}"
                } else {
                    publication.name
                }
                ClickableTextLine(
                    modifier = Modifier.fillMaxWidth(),
                    text = name
                ) {
                    when {
                        publication.name.contains("Imperial Armour") -> {
                            navigator.navigate(
                                PublicationDatasheetsDestination(
                                    name,
                                    publication.id,
                                    viewModel.factionImageState,
                                )
                            )
                        }

                        publication.combatPatrolName != null -> {
                            navigator.navigate(
                                PatrolMenuDestination(
                                    name,
                                    publication.id,
                                    publication.factionBackgroundImage
                                )
                            )
                        }

                        else -> {
                            navigator.navigate(
                                IndexScreenDestination(
                                    viewModel.factionNameState,
                                    viewModel.factionIdState,
                                    viewModel.factionImageState,
                                    viewModel.isSubFactionState,
                                    publication.id
                                )
                            )
                        }
                    }
                }
            }
            if (viewModel.publications.isEmpty()) {
                item {
                    ClickableTextLine(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.regular_datasheets)
                    ) {
                        navigator.navigate(
                            DataSheetListDestination(
                                factionName = viewModel.factionNameState,
                                factionId = viewModel.factionIdState,
                                isSubFaction = viewModel.isSubFactionState,
                                isPatrol = false,
                                isFavorites = false
                            )
                        )
                    }
                }
            }
            item {
                ClickableTextLine(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.favorite_units)
                ) {
                    navigator.navigate(
                        DataSheetListDestination(
                            factionName = viewModel.factionNameState,
                            factionId = viewModel.factionIdState,
                            isSubFaction = viewModel.isSubFactionState,
                            isPatrol = false,
                            isFavorites = true
                        )
                    )
                }
            }
        }
    })
}