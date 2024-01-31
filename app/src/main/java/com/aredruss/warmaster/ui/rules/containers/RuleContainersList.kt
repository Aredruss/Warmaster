package com.aredruss.warmaster.ui.rules.containers

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.ui.abilityInfo.FactionAbilityView
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.common.CollapsableIsland
import com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules.StrategemView
import com.aredruss.warmaster.ui.destinations.RuleContainersListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun RuleContainersList(
    sectionName: String, sectionId: String, navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<RuleContainersViewModel> {
        parametersOf(sectionName, sectionId)
    }

    Scaffold(topBar = {
        CenteredTopBar(title = viewModel.publicationNameState, navigationAction = {
            navigator.popBackStack()
        })
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            Crossfade(
                modifier = Modifier.fillMaxHeight(),
                targetState = viewModel.loadingState,
                label = ""
            ) { loading ->
                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(size = 40.dp))
                    }
                } else {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        viewModel.containers.forEach { container ->
                            if (container.containerType == "stratagem") {
                                container.childStrategem?.let { stratagem ->
                                    StrategemView(strategem = stratagem)
                                }
                            } else {
                                val subtitle =
                                    if (container.subtitle != null) {
                                        ": ${container.subtitle}"
                                    } else {
                                        ""
                                    }
                                CollapsableIsland(modifier = Modifier.fillMaxWidth(),
                                    title = "${container.title}${subtitle}",
                                    content = {
                                        FactionAbilityView(
                                            modifier = Modifier.fillMaxWidth(),
                                            rules = container.childContainerComponents,
                                            shouldShowFactionHeader = false
                                        )
                                    })
                            }
                        }
                        viewModel.subSections.forEach { container ->
                            ClickableTextLine(
                                modifier = Modifier.fillMaxWidth(), text = container.name
                            ) {
                                navigator.navigate(
                                    RuleContainersListDestination(
                                        container.name, container.id
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