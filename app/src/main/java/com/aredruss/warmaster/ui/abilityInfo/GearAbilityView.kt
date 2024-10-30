package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.domain.database.model.WargearAbility

@Composable
fun GearAbilityView(modifier: Modifier, ability: WargearAbility) {
    Column(
        modifier = modifier
            .padding(all = 10.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color.White)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            text = ability.rules,
            textAlign = TextAlign.Justify,
            color = Color.Black,
            style = MaterialTheme.typography.labelMedium
        )
    }
}