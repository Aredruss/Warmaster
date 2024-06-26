package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.ui.theme.md_theme_dark_surface

@Composable
fun GradientAsyncImage(image: String, height: Int) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(height = height.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            md_theme_dark_surface
                        ),
                        startY = size.height / 2,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Darken)
                    }
                },
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            filterQuality = FilterQuality.High
        )
    }
}