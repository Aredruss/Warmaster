package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.destinations.AboutWarmasterDestination
import com.aredruss.warmaster.ui.destinations.SearchScreenDestination
import com.aredruss.warmaster.ui.theme.WarmasterTheme
import com.aredruss.warmaster.ui.theme.md_theme_dark_surface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopBar(
    title: String,
    navigationAction: (() -> Unit)? = null,
    enableAdditionalAction: Boolean = false,
    additionalActionIcon: Int? = null,
    additionalAction: (() -> Unit)? = null,
    secondAdditionalActionIcon: Int? = null,
    secondAdditionalAction: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.shadow(elevation = 5.dp),
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
                        painter = painterResource(id = R.drawable.ic_left_arrow),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            if (enableAdditionalAction) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { additionalAction?.invoke() }) {
                        Icon(
                            painter = painterResource(
                                id = additionalActionIcon ?: R.drawable.ic_info
                            ),
                            contentDescription = null
                        )
                    }
                    secondAdditionalAction?.let {
                        IconButton(onClick = { secondAdditionalAction.invoke() }) {
                            Icon(
                                painter = painterResource(
                                    id = secondAdditionalActionIcon ?: R.drawable.ic_info
                                ),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = md_theme_dark_surface
        )
    )
}

@Preview
@Composable
private fun NavbarPreview() {
    WarmasterTheme {
        Scaffold(topBar = {
            CenteredTopBar(
                title = stringResource(id = R.string.factions),
                enableAdditionalAction = true,
                additionalActionIcon = R.drawable.ic_search,
                additionalAction = {
                },
                secondAdditionalAction = {
                }
            )
        }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }

    }
}