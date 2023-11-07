package com.aredruss.warmaster.ui.datasheets.gameType

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.aredruss.warmaster.ui.common.ClickableTextCard
import com.aredruss.warmaster.ui.destinations.DataSheetListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun GameType(
    factionName: String,
    factionId: String,
    factionImage: String,
    isSubFaction: Boolean,
    navigator: DestinationsNavigator,
) {

    val viewModel = getViewModel<GameTypeViewModel> {
        parametersOf(factionName, factionId, isSubFaction, factionImage)
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
        CenteredTopBar(title = viewModel.factionNameState,
            navigationAction = { navigator.popBackStack() })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 125.dp),
                model = viewModel.factionImageState,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                filterQuality = FilterQuality.High
            )
            ClickableTextCard(
                modifier = Modifier, text = stringResource(id = R.string.regular_datasheets)
            ) {
                navigateToDatasheetList(isPatrol = false, isFavorites = false)
            }
            ClickableTextCard(
                modifier = Modifier,
                text = stringResource(id = R.string.combat_patrol_datasheets)
            ) {
                navigateToDatasheetList(isPatrol = true, isFavorites = false)
            }
            ClickableTextCard(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.favorite_units)
            ) {
                navigateToDatasheetList(isPatrol = false, isFavorites = true)
            }
        }
    })
}