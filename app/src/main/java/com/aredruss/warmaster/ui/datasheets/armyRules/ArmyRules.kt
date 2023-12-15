package com.aredruss.warmaster.ui.datasheets.armyRules

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
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
import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.common.InfoMessage
import com.aredruss.warmaster.ui.datasheets.DatasheetItem
import com.aredruss.warmaster.ui.destinations.AbilityScreenDestination
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun ArmyRules(
    pubName: String,
    publicationId: String,
    publicationImage: String,
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<ArmyRulesViewModel> {
        parametersOf(pubName, publicationId, publicationImage)
    }

    Scaffold(topBar = {
        CenteredTopBar(title = viewModel.publicationNameState,
            navigationAction = {
                navigator.popBackStack()
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
                        .height(height = 100.dp),
                    model = viewModel.publicationImageState,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center,
                    filterQuality = FilterQuality.High
                )
            }
            item {
                Spacer(modifier = Modifier.height(height = 10.dp))
            }

            if (viewModel.rules.isEmpty()) {
                item {
                    InfoMessage(text = stringResource(R.string.generic_empty_data), action = {})
                }
            } else {
                items(viewModel.rules) { rule ->
                    ClickableTextLine(
                        modifier = Modifier.fillMaxWidth(),
                        text = rule.name
                    ) {
                        navigator.navigate(
                            AbilityScreenDestination(
                                rule.name,
                                rule.id,
                                AbilityInfoViewModel.Companion.ScreenType.PUBLICATION
                            )
                        )
                    }
                }
            }
        }
    })
}