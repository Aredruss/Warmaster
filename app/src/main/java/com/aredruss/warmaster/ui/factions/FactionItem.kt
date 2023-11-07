package com.aredruss.warmaster.ui.factions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.domain.database.model.FactionKeyword


@Composable
fun FactionItem(
    factionKeyword: FactionKeyword,
    action: (String, String, String, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                action.invoke(
                    factionKeyword.name,
                    factionKeyword.id,
                    factionKeyword.rosterHeaderImage ?: "",
                    factionKeyword.parentFactionKeywordId != null
                )
            }
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp)
            .clip(shape = ShapeDefaults.Small)
            .fillMaxWidth()
            .height(height = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = factionKeyword.rosterHeaderImage,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.4f),
                blendMode = BlendMode.Darken
            ),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.Low
        )
        Text(
            text = factionKeyword.name,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )
    }
}