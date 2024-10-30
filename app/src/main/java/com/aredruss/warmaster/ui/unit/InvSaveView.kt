package com.aredruss.warmaster.ui.unit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.InvSave
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary

@Composable
fun InvSaveView(
    modifier: Modifier, invSave: InvSave
) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )
    val shouldExpand = invSave.rules != null

    Column(
        modifier = modifier
            .background(color = md_theme_dark_onPrimary)
            .clickable {
                expandedState = !expandedState
            }
    ) {
        Row(
            modifier = Modifier.clip(
                shape = RoundedCornerShape(
                    topStart = 5.dp, topEnd = 5.dp
                )
            ), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = stringResource(id = R.string.inv_save_rule),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Start,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 5.dp,
                        vertical = 5.dp
                    )
                    .size(size = 35.dp)
                    .clip(shape = RoundedCornerShape(size = 5.dp))
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = invSave.save,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            if (shouldExpand) {
                IconButton(modifier = Modifier.rotate(rotationState), onClick = {
                    expandedState = !expandedState
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        if (shouldExpand) {
            AnimatedVisibility(
                visible = expandedState
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .padding(all = 10.dp),
                    text = invSave.rules ?: "",
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
    }
}