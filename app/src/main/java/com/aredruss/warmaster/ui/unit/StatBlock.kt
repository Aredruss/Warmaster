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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.data.model.InvSave
import com.aredruss.warmaster.data.model.Miniature
import timber.log.Timber

@Composable
fun StatBlock(miniature: Miniature, invSave: InvSave?) {
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
            invSave?.let {
                StatItem(title = "IS", value = invSave.save)
            }
        }
        invSave?.rules?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .padding(horizontal = 10.dp),
                text = it,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}