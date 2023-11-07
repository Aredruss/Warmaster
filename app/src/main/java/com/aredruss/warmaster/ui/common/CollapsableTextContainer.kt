package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CollapsableTextContainer(
    modifier: Modifier,
    title: String,
    content: String,
    icon: Int? = null
) {
    CollapsableContainer(
        modifier = modifier,
        title = title,
        iconId = icon
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                text = content,
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}