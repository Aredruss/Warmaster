package com.aredruss.warmaster.ui.datasheets.index

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.datasheets.detachment.DetachmentItem
import com.aredruss.warmaster.ui.destinations.ArmyRulesDestination
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.aredruss.warmaster.ui.destinations.DetachmentViewDestination
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun IndexScreen(
    factionName: String,
    factionId: String,
    factionImage: String,
    isSubFaction: Boolean,
    publicationId: String,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<IndexScreenViewModel> {
        parametersOf(factionName, factionId, isSubFaction, factionImage, publicationId)
    }

    fun navigateToDatasheetList(
        isPatrol: Boolean,
        isFavorites: Boolean
    ) {
        navigator.navigate(
            DataSheetListDestination(
                factionName = viewModel.factionNameState,
                factionId = viewModel.factionIdState,
                isSubFaction = viewModel.isSubFactionState,
                isPatrol = isPatrol,
                isFavorites = isFavorites
            )
        )
    }

    Scaffold(topBar = {
        CenteredTopBar(
            title = viewModel.factionNameState,
            navigationAction = {
                navigator.popBackStack()
            },
            additionalActionIcon = R.drawable.ic_search,
            enableAdditionalAction = true,
            additionalAction = {
                navigator.navigate(
                    SearchScreenDestination(
                        viewModel.factionIdState,
                        viewModel.isSubFactionState
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
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(id = R.string.regular_datasheets)
                ) {
                    navigateToDatasheetList(
                        isPatrol = false,
                        isFavorites = false
                    )
                }
            }
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(R.string.army_rules)
                ) {
                    navigator.navigate(
                        ArmyRulesDestination(
                            pubName = viewModel.factionNameState,
                            publicationId = viewModel.publicationIdState,
                            publicationImage = viewModel.factionImageState
                        )
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(height = 20.dp))
            }
            if (viewModel.detachments.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        text = stringResource(R.string.detachments),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                items(viewModel.detachments) { detachment ->
                    DetachmentItem(
                        detachment = detachment,
                        action = { id, name ->
                            navigator.navigate(
                                DetachmentViewDestination(
                                    detachmentId = id,
                                    detachmentName = name
                                )
                            )
                        })
                }
            }

            item {
                Spacer(modifier = Modifier.height(height = 20.dp))
            }
        }
    })
}