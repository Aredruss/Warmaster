package com.aredruss.warmaster.ui.rules.sections

import androidx.compose.animation.Crossfade
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.aredruss.warmaster.domain.database.model.RuleSection
import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.common.InfoMessage
import com.aredruss.warmaster.ui.destinations.AbilityScreenDestination
import com.aredruss.warmaster.ui.destinations.RuleContainersListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun Rulelist(
    pubName: String,
    publicationId: String,
    publicationImage: String,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<RuleSectionListViewModel> {
        parametersOf(pubName, publicationId, publicationImage)
    }

    Scaffold(topBar = {
        CenteredTopBar(title = viewModel.publicationNameState,
            navigationAction = {
                navigator.popBackStack()
            })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues = it)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 200.dp),
                model = viewModel.publicationImageState,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                filterQuality = FilterQuality.High
            )

            Spacer(modifier = Modifier.height(height = 10.dp))

            Crossfade(targetState = viewModel.loadingState, label = "") { loading ->
                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(size = 40.dp))
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (viewModel.sections.isEmpty()) {
                            InfoMessage(
                                text = stringResource(R.string.generic_empty_data),
                                action = {})
                        } else {
                            viewModel.sections.forEach { rule ->
                                ClickableTextLine(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = rule.name
                                ) {
                                    navigator.navigate(
                                        RuleContainersListDestination(
                                            rule.name,
                                            rule.id
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    })
}