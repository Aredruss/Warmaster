package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextBlock(
    modifier: Modifier,
    textTitle: String,
    textValue: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            text = textTitle,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp
                ),
            text = textValue,
            textAlign = TextAlign.Justify,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            fontStyle = FontStyle.Italic
        )
    }
}