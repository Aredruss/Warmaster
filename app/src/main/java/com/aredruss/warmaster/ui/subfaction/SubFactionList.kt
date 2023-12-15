package com.aredruss.warmaster.ui.subfaction

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.aredruss.warmaster.ui.datasheets.menu.GameMenu
import com.aredruss.warmaster.ui.destinations.GameMenuDestination
import com.aredruss.warmaster.ui.factions.FactionItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SubFactionList(
    factionId: String, navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<SubFactionViewModel>() {
        parametersOf(factionId)
    }

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(id = R.string.subfactions),
                navigationAction = { navigator.popBackStack() }
            )
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                Crossfade(
                    targetState = viewModel.loadingState,
                    label = "",
                    modifier = Modifier.fillMaxSize(),
                ) { isLoading ->
                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(size = 40.dp))
                        }
                    } else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(viewModel.factionKeywordList) {
                                FactionItem(factionKeyword = it) { name, id, image, isSubFaction ->
                                    navigator.navigate(
                                        GameMenuDestination(
                                            factionId = id,
                                            factionName = name,
                                            factionImage = image,
                                            isSubFaction = isSubFaction
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        })
}
