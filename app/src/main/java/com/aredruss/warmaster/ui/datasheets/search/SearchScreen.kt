package com.aredruss.warmaster.ui.datasheets.search

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
import com.aredruss.warmaster.ui.common.InfoMessage
import com.aredruss.warmaster.ui.common.SearchTopBar
import com.aredruss.warmaster.ui.datasheets.DatasheetItem
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SearchScreen(
    publicationId: String = "",
    factionId: String = "",
    useFactionSearch:Boolean = false,
    isSubfaction: Boolean = false,
    navigator: DestinationsNavigator
) {

    val searchViewModel = getViewModel<SearchViewModel> {
        parametersOf(publicationId, factionId, useFactionSearch, isSubfaction)
    }

    Scaffold(
        topBar = {
            SearchTopBar(
                queryCallback = { query ->
                    searchViewModel.getData(query)
                },
                navigationAction = {
                    navigator.popBackStack()
                },
                clearQueryCallback = {
                    searchViewModel.clearList()
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                Crossfade(
                    targetState = searchViewModel.loadingState,
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
                        if (searchViewModel.datasheetList.isNotEmpty()) {
                            LazyColumn {
                                items(searchViewModel.datasheetList) { data ->
                                    DatasheetItem(datasheet = data) { id ->
                                        navigator.navigate(
                                            UnitPageDestination(id)
                                        )
                                    }
                                }
                            }
                        } else {
                            InfoMessage(
                                text = stringResource(R.string.no_datasheets_matching_that_input),
                                action = {})
                        }
                    }
                }
            }
        })
}