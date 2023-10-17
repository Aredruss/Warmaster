package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.theme.md_theme_dark_primaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopBar(
    title: String,
    navigationAction: (() -> Unit)? = null,
    additionalActionIcon: Int? = null,
    additionalAction: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        navigationIcon = {
            navigationAction?.let {
                IconButton(onClick = { it.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_right_arrow),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            additionalAction?.let {
                IconButton(onClick = { it.invoke() }) {
                    Icon(
                        painter = painterResource(
                            id = additionalActionIcon ?: R.drawable.ic_info
                        ),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = md_theme_dark_primaryContainer
        )
    )
}