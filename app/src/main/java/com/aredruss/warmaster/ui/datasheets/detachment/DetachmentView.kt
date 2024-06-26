package com.aredruss.warmaster.ui.datasheets.detachment

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.abilityInfo.FactionAbilityView
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.ClickableTextLine
import com.aredruss.warmaster.ui.common.CollapsableIsland
import com.aredruss.warmaster.ui.common.GradientAsyncImage
import com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules.MinorRulesViewModel
import com.aredruss.warmaster.ui.destinations.MinorRulesListDestination
import com.aredruss.warmaster.ui.theme.md_theme_dark_tertiaryContainer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun DetachmentView(
    detachmentId: String,
    detachmentName: String,
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<DetachmentViewModel> {
        parametersOf(detachmentId, detachmentName)
    }

    Scaffold(topBar = {
        CenteredTopBar(
            title = viewModel.detachmentNameState,
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
                GradientAsyncImage(
                    image = viewModel.detachmentState?.bannerImage ?: "",
                    height = 200
                )
            }
            item {
                Spacer(modifier = Modifier.height(height = 10.dp))
            }
            item {
                ClickableTextLine(
                    modifier = Modifier,
                    text = stringResource(R.string.enhancements)
                ) {
                    navigator.navigate(
                        MinorRulesListDestination(
                            parentId = viewModel.detachmentState?.id ?: "",
                            shouldUseDetachments = true,
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
                            parentId = viewModel.detachmentState?.id ?: "",
                            shouldUseDetachments = true,
                            ruleScreenType = MinorRulesViewModel.Companion.RuleScreenType.STRATEGEM
                        )
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    text = stringResource(R.string.detachment_rules),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(viewModel.detachmentRuleState) { rule ->
                CollapsableIsland(
                    modifier = Modifier.fillMaxWidth(),
                    title = rule.name,
                    titleBackground = md_theme_dark_tertiaryContainer,
                    content = {
                        FactionAbilityView(
                            modifier = Modifier,
                            rules = rule.rules,
                            shouldShowFactionHeader = false
                        )
                    }
                )
            }
            item {
                viewModel.detachmentState?.bullets?.forEach { bullet ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 5.dp),
                        text = "• " + bullet.text,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    })
}