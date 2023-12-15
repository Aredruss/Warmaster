package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary

@Composable
fun CollapsableIsland(
    modifier: Modifier,
    title: String,
    icon: Int? = null,
    minorLeftText: String? = null,
    content: @Composable () -> Unit,
    titleBackground: Color = md_theme_dark_onPrimary
) {
    CollapsableContainer(
        modifier = modifier
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp)
            .clip(shape = ShapeDefaults.Small),
        title = title,
        iconId = icon,
        contentBackground = Color.White,
        titleBackground = titleBackground,
        minorLeftText = minorLeftText
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            content.invoke()
        }
    }
}