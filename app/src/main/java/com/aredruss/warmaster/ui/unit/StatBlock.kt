package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.aredruss.warmaster.domain.database.model.Miniature

@Composable
fun StatBlock(
    miniature: Miniature
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondary),
            horizontalArrangement = Arrangement.Center
        ) {
            StatItem(title = "M", value = miniature.movement)
            StatItem(title = "T", value = miniature.toughness)
            StatItem(title = "SV", value = miniature.save)
            StatItem(title = "W", value = miniature.wounds)
            StatItem(title = "LD", value = miniature.leadership)
            StatItem(title = "OC", value = miniature.objectiveControl)
        }
    }
}