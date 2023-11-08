package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.index.AggregatedAbilities
import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility
import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.common.CollapsableContainer
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary
import com.aredruss.warmaster.util.uppercaseFirst

@Composable
fun AbilitiesView(
    modifier: Modifier,
    abilities: AggregatedAbilities,
    onClick: (String, String, AbilityInfoViewModel.Companion.ScreenType) -> Unit
) {
    CollapsableContainer(
        title = stringResource(R.string.abilities),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            AbilityBlockHeader(title = stringResource(id = R.string.faction))
            abilities.factionAbilities?.let { abilities ->
                AbilitiesFlowRow(
                    abilities = abilities.map { it.first },
                    onClick = onClick
                )
            }
            Spacer(modifier = Modifier.height(height = 5.dp))
            abilities.normalAbilities.keys.forEach { key ->
                AbilityBlockHeader(title = key.uppercaseFirst())
                if (key == "core") {
                    abilities.normalAbilities[key]?.let {
                        AbilitiesFlowRow(
                            abilities = it,
                            onClick = onClick
                        )
                    }
                } else {
                    abilities.normalAbilities[key]?.forEach { ability ->
                        FullAbilityView(ability = ability)
                        Spacer(modifier = Modifier.height(height = 5.dp))
                    }
                }
                Spacer(modifier = Modifier.height(height = 5.dp))
            }
        }
    }
}

@Composable
fun SubAbilitiesView(
    modifier: Modifier,
    abilities: Map<String, List<DatasheetSubAbility>>,
) {
    abilities.keys.forEach { key ->
        CollapsableContainer(
            title = key.uppercaseFirst(),
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                abilities[key]?.forEach { ability ->
                    SubAbilityView(ability = ability)
                    Spacer(modifier = Modifier.height(height = 5.dp))
                }

                Spacer(modifier = Modifier.height(height = 5.dp)) }
        }
    }

}

@Composable
fun MiniAbilityView(
    ability: DatasheetAbility,
    onClick: ((String, String, AbilityInfoViewModel.Companion.ScreenType) -> Unit)? = null
) {
    Text(
        modifier = Modifier
            .padding(start = 5.dp, top = 10.dp)
            .background(
                color = md_theme_dark_onPrimary,
                shape = MaterialTheme.shapes.extraSmall
            )
            .clickable {
                onClick?.invoke(
                    ability.id,
                    ability.name,
                    if (ability.abilityType == "faction") {
                        AbilityInfoViewModel.Companion.ScreenType.FACTION
                    } else {
                        AbilityInfoViewModel.Companion.ScreenType.DATASHEET
                    }
                )
            }
            .padding(all = 5.dp),
        text = ability.name,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        color = Color.White,
        style = MaterialTheme.typography.labelMedium
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AbilitiesFlowRow(
    abilities: List<DatasheetAbility>,
    onClick: (String, String, AbilityInfoViewModel.Companion.ScreenType) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        abilities.forEach {
            MiniAbilityView(ability = it, onClick = onClick)
        }
    }
}

@Composable
fun FullAbilityView(
    ability: DatasheetAbility
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        MiniAbilityView(ability = ability)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp),
            text = ability.rules ?: "-",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun SubAbilityView(
    ability: DatasheetSubAbility
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        MiniSubAbilityView(ability = ability)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp),
            text = ability.rules ?: "-",
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun MiniSubAbilityView(
    ability: DatasheetSubAbility,
) {
    Text(
        modifier = Modifier
            .padding(start = 5.dp, top = 10.dp)
            .background(
                color = md_theme_dark_onPrimary,
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(all = 5.dp),
        text = ability.name,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Center,
        color = Color.White,
        style = MaterialTheme.typography.labelMedium
    )
}


@Composable
fun AbilityBlockHeader(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp),
        text = "$title:",
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.onSecondary,
        style = MaterialTheme.typography.labelLarge
    )
}


