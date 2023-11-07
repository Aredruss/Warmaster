package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.CollapsableContainer
import com.aredruss.warmaster.ui.common.CollapsableTextContainer
import com.aredruss.warmaster.ui.destinations.AbilityScreenDestination
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun UnitPage(
    datasheetId: String,
    factionId: String,
    navigator: DestinationsNavigator
) {

    val unitViewModel = getViewModel<UnitPageViewModel> {
        parametersOf(datasheetId, factionId)
    }

    fun navigateToAbility(
        abilityId: String,
        abilityName: String,
        type: AbilityInfoViewModel.Companion.ScreenType
    ) {
        navigator.navigate(
            AbilityScreenDestination(
                abilityId = abilityId,
                abilityName = abilityName,
                abilityType = type
            )
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenteredTopBar(
                title = unitViewModel.datasheet?.name ?: "Unknown",
                navigationAction = { navigator.popBackStack() },
                additionalActionIcon = if (unitViewModel.isFavorite == true) {
                    R.drawable.ic_fav_filled
                } else {
                    R.drawable.ic_fav_empty
                },
                enableAdditionalAction = unitViewModel.isFavorite != null,
                additionalAction = {
                    unitViewModel.setFavoriteState()
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier =
                Modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize()
            ) {
                item {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = unitViewModel.datasheet?.bannerImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.High
                    )
                }
                items(unitViewModel.miniatureList) { mini ->
                    if (unitViewModel.miniatureList.size > 1) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = md_theme_dark_onPrimary)
                                .padding(all = 5.dp),
                            text = mini.name,
                            style = MaterialTheme.typography.labelLarge,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    if (!mini.statlineHidden) {
                        StatBlock(miniature = mini)
                    }
                }
                unitViewModel.invSave?.let {
                    item {
                        InvSaveView(modifier = Modifier.fillMaxWidth(), invSave = it)
                    }
                }
                unitViewModel.datasheetWargear?.let {
                    item {
                        WeaponProfileViews(
                            modifier = Modifier.fillMaxWidth(),
                            profiles = it,
                            onAbilityClick = { id, name ->
                                navigateToAbility(
                                    abilityId = id,
                                    abilityName = name,
                                    type = AbilityInfoViewModel.Companion.ScreenType.GEAR
                                )
                            }
                        )
                    }
                }
                unitViewModel.datasheetAbilities?.let {
                    item {
                        AbilitiesView(
                            modifier = Modifier.fillMaxWidth(),
                            abilities = it,
                            onClick = { id, name, type ->
                                navigateToAbility(
                                    abilityId = id,
                                    abilityName = name,
                                    type = type
                                )
                            }
                        )
                    }
                }
                unitViewModel.customWargearAbilities?.let { itemRules ->
                    items(itemRules) {
                        CollapsableTextContainer(
                            modifier = Modifier.fillMaxWidth(),
                            title = it.name,
                            content = it.ruleText ?: "",
                            icon = R.drawable.ic_books
                        )
                    }
                }
                items(unitViewModel.ruleset ?: emptyList()) { rule ->
                    CollapsableTextContainer(
                        modifier = Modifier.fillMaxWidth(),
                        title = rule.name,
                        content = rule.rules,
                        icon = R.drawable.ic_books
                    )
                }
                unitViewModel.wargearOptionRules?.let { rules ->
                    if (rules.isNotEmpty()) {
                        item {
                            WargearOptionView(
                                modifier = Modifier.fillMaxWidth(),
                                rules = rules
                            )
                        }
                    }
                }
                unitViewModel.datasheet?.unitComposition?.let {
                    item {
                        CollapsableTextContainer(
                            title = stringResource(R.string.unit_info),
                            modifier = Modifier.fillMaxWidth(),
                            content = it
                        )
                    }
                }
                unitViewModel.unitComposition?.let { composition ->
                    if (composition.isNotEmpty()) {
                        item {
                            UnitCompositionView(
                                modifier = Modifier.fillMaxWidth(),
                                items = composition
                            )
                        }
                    }
                }
                unitViewModel.keywords?.let { list ->
                    item {
                        CollapsableContainer(
                            modifier = Modifier.fillMaxWidth(),
                            title = stringResource(R.string.unit_keywords)
                        ) {
                            UnitKeywords(
                                modifier = Modifier
                                    .padding(
                                        vertical = 5.dp,
                                        horizontal = 10.dp
                                    ),
                                unitKeywords = list
                            )
                        }
                    }
                }
                unitViewModel.datasheet?.lore?.let {
                    item {
                        LoreView(modifier = Modifier, lore = it)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(height = 30.dp))
                }
            }
        }
    )
}