package com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.CollapsableIsland
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun MinorRulesList(
    parentId: String,
    shouldUseDetachments: Boolean,
    ruleScreenType: MinorRulesViewModel.Companion.RuleScreenType,
    navigator: DestinationsNavigator
) {

    val ruleViewModel = getViewModel<MinorRulesViewModel> {
        parametersOf(parentId, shouldUseDetachments, ruleScreenType)
    }

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(
                    id = when (ruleViewModel.ruleScreenTypeState) {
                        MinorRulesViewModel.Companion.RuleScreenType.ENHANCE -> {
                            R.string.enhancements
                        }

                        MinorRulesViewModel.Companion.RuleScreenType.STRATEGEM -> {
                            R.string.strategems
                        }

                        else -> {
                            R.string.secondary_objectives
                        }
                    }
                ),
                navigationAction = { navigator.popBackStack() })
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = it)
            ) {
                when (ruleViewModel.ruleScreenTypeState) {
                    MinorRulesViewModel.Companion.RuleScreenType.ENHANCE -> {
                        items(ruleViewModel.enhancements) { rule ->
                            val ruleCost = if (rule.basePointsCost != null) {
                                "${(rule.basePointsCost)} P"
                            } else {
                                null
                            }
                            CollapsableIsland(
                                modifier = Modifier.fillMaxWidth(),
                                title = rule.name,
                                minorLeftText = ruleCost,
                                content = {
                                    BasicMinorText(text = rule.rules)
                                })
                        }
                    }

                    MinorRulesViewModel.Companion.RuleScreenType.STRATEGEM -> {
                        items(ruleViewModel.strategems) { strategem ->
                            StrategemView(strategem = strategem)
                        }
                    }

                    else -> {
                        items(ruleViewModel.secondaryObjectives) { objective ->
                            CollapsableIsland(
                                modifier = Modifier.fillMaxWidth(),
                                title = objective.name,
                                content = {
                                    BasicMinorText(text = objective.rules ?: "")
                                })
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BasicMinorText(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        text = text,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Justify,
        color = Color.Black.copy(alpha = 0.8F),
        style = MaterialTheme.typography.labelLarge
    )
}