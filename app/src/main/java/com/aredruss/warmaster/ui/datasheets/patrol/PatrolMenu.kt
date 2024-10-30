package com.aredruss.warmaster.ui.datasheets.patrol

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules.MinorRulesViewModel
import com.aredruss.warmaster.ui.destinations.ArmyRulesDestination
import com.aredruss.warmaster.ui.destinations.MinorRulesListDestination
import com.aredruss.warmaster.ui.destinations.PublicationDatasheetsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatrolMenu(
    factionName: String,
    publicationId: String,
    factionImage: String,
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<PatrolViewModel> {
        parametersOf(factionName, publicationId, factionImage)
    }

    Scaffold(topBar = {
        CenteredTopBar(
            title = viewModel.factionNameState,
            navigationAction = {
                navigator.popBackStack()
            },

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
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(id = R.string.regular_datasheets)
                ) {
                    navigator.navigate(
                        PublicationDatasheetsDestination(
                            viewModel.factionNameState,
                            viewModel.publicationIdState,
                            viewModel.factionImageState,
                        )
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
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(R.string.enhancements)
                ) {
                    navigator.navigate(
                        MinorRulesListDestination(
                            parentId = viewModel.publicationIdState,
                            shouldUseDetachments = false,
                            ruleScreenType = MinorRulesViewModel.Companion.RuleScreenType.ENHANCE
                        )
                    )
                }
            }
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(R.string.strategems)
                ) {
                    navigator.navigate(
                        MinorRulesListDestination(
                            parentId = viewModel.publicationIdState,
                            shouldUseDetachments = false,
                            ruleScreenType = MinorRulesViewModel.Companion.RuleScreenType.STRATEGEM
                        )
                    )
                }
            }
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(R.string.secondary_objectives)
                ) {
                    navigator.navigate(
                        MinorRulesListDestination(
                            parentId = viewModel.publicationIdState,
                            shouldUseDetachments = false,
                            ruleScreenType = MinorRulesViewModel.Companion.RuleScreenType.SECONDARY
                        )
                    )
                }
            }
        }
    })
}