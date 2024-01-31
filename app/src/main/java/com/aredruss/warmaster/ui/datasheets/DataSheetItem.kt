package com.aredruss.warmaster.ui.datasheets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.domain.database.model.Datasheet

@Composable
fun DatasheetItem(
    datasheet: Datasheet,
    action: (String) -> Unit
) {
    val subtext = if (datasheet.publicationName != null) "\n(${datasheet.publicationName})" else ""
    Box(
        modifier = Modifier
            .clickable {
                action.invoke(datasheet.id)
            }
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(ShapeDefaults.Medium)
            .fillMaxWidth()
            .height(height = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray),
            model = datasheet.bannerImage,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                color = Color.Black.copy(alpha = 0.7f),
                blendMode = BlendMode.Darken
            ),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.High
        )
        Text(
            text = datasheet.name + subtext,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}