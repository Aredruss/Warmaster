package com.aredruss.warmaster.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.theme.md_theme_dark_surface
import kotlinx.coroutines.android.awaitFrame

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    navigationAction: (() -> Unit)? = null,
    queryCallback: (String) -> Unit,
    clearQueryCallback: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
    }

    CenterAlignedTopAppBar(
        modifier = Modifier
            .shadow(elevation = 5.dp),
        title = {
            TextField(
                modifier = Modifier.focusRequester(focusRequester),
                placeholder = {
                    Text(
                        text = stringResource(R.string.datasheet_name),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                value = query,
                onValueChange = {
                    query = it
                    queryCallback.invoke(it)
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                    }),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent
                )
            )
        },
        navigationIcon = {
            navigationAction?.let {
                IconButton(onClick = {
                    it.invoke()
                    clearQueryCallback.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_left_arrow),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { query = "" }) {
                Icon(
                    modifier = Modifier.size(size = 20.dp),
                    painter = painterResource(
                        id = R.drawable.ic_cancel
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = md_theme_dark_surface
        )
    )
}