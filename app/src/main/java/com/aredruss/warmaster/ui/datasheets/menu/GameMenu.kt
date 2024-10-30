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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.common.GradientAsyncImage
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.destinations.IndexScreenDestination
import com.aredruss.warmaster.ui.destinations.PatrolMenuDestination
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
        parametersOf(factionName, factionId, factionImage, isSubFaction)
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
                        publicationId = "",
                        factionId = viewModel.factionIdState,
                        useFactionSearch = true
                    )
                )
            }
        )
    }, content = {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            item {
                GradientAsyncImage(image = viewModel.factionImageState, height = 200)
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
                                    factionName = viewModel.factionNameState,
                                    factionId = viewModel.factionIdState,
                                    factionImage = viewModel.factionImageState,
                                    false,
                                    publicationId = publication.id
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
                            isFavorites = true
                        )
                    )
                }
            }
        }
    })
}