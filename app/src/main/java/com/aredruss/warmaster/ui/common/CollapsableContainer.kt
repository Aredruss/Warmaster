package com.aredruss.warmaster.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary

@Composable
fun CollapsableContainer(
    modifier: Modifier,
    title: String = "",
    minorLeftText: String? = null,
    iconId: Int? = null,
    titleBackground: Color = md_theme_dark_onPrimary,
    contentBackground: Color = MaterialTheme.colorScheme.secondary,
    content: @Composable () -> Unit
) {

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    Column(
        modifier = modifier
            .clickable {
                expandedState = !expandedState
            }
            .background(color = titleBackground)
    ) {
        Row(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 5.dp,
                        topEnd = 5.dp
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconId?.let {
                Icon(
                    modifier = Modifier.padding(start = 10.dp, end = 5.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = "icon"
                )
            }
            Spacer(modifier = Modifier.width(width = 10.dp))
            Text(
                text = title,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(end = 20.dp),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Start,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            minorLeftText?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(horizontal = 5.dp),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(
                modifier = Modifier
                    .rotate(rotationState),
                onClick = {
                    expandedState = !expandedState
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        AnimatedVisibility(
            visible = expandedState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = contentBackground)
            ) {
                content()
            }
        }

    }
}
