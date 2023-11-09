package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.domain.database.model.WargearAbility
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import com.aredruss.warmaster.ui.common.CollapsableContainer
import com.aredruss.warmaster.util.uppercaseFirst

@Composable
fun WeaponProfileViews(
    modifier: Modifier,
    profiles: Map<String, List<WargearItemProfile>>,
    onAbilityClick: (String, String) -> Unit
) {
    profiles.toList().forEach { profileBlock ->
        WeaponProfileView(
            modifier = modifier,
            profile = profileBlock,
            onAbilityClick = onAbilityClick
        )
    }
}

@Composable
fun WeaponProfileView(
    modifier: Modifier,
    profile: Pair<String, List<WargearItemProfile>>,
    onAbilityClick: (String, String) -> Unit
) {
    CollapsableContainer(modifier = modifier, title = profile.first.uppercaseFirst()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            WeaponProfileRow(
                true, listOf(
                    "Range", "A", "Skill", "S", "AP", "D"
                )
            )
            profile.second.forEach { profile ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()

                        .padding(top = 5.dp, start = 15.dp),
                    text = profile.wargearItemName ?: "",
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelLarge
                )
                WeaponProfileRow(
                    isHeader = false,
                    args = listOf(
                        profile.range,
                        profile.attacks,
                        profile.ballisticSkill ?: profile.weaponSkill ?: "",
                        profile.strength,
                        profile.armourPenetration,
                        profile.damage
                    )
                )
                Spacer(modifier = Modifier.height(height = 5.dp))
                WeaponAbilityList(
                    abilities = profile.wargearAbilities,
                    onClick = onAbilityClick
                )
                Spacer(modifier = Modifier.height(height = 5.dp))
            }
        }
    }
}

@Composable
fun WeaponProfileRow(isHeader: Boolean, args: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
    ) {
        args.forEach {
            WeaponStatText(
                text = it,
                modifier = Modifier,
                isHeader = isHeader
            )
        }
    }
}

@Composable
fun RowScope.WeaponStatText(
    modifier: Modifier,
    text: String,
    isHeader: Boolean
) {
    Text(
        text = text,
        modifier = modifier.weight(weight = 1f),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSecondary,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = if (isHeader) FontWeight.Bold else FontWeight.SemiBold
    )
}

@Composable
fun WeaponAbilityList(
    abilities: List<WargearAbility>,
    onClick: (String, String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        abilities.forEach {
            Text(
                modifier = Modifier
                    .padding(top = 1.dp)
                    .padding(horizontal = 5.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .clickable {
                        onClick.invoke(
                            it.id, it.name
                        )
                    }
                    .padding(all = 5.dp),
                text = it.name,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}