package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.domain.database.model.Miniature
import com.aredruss.warmaster.ui.common.TextBlock

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