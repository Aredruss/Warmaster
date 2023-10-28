package com.aredruss.warmaster.ui.datasheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.destinations.UnitPageDestination
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun DataSheetList(
    factionName: String,
    factionId: String,
    navigator: DestinationsNavigator
) {
    val viewModel = getViewModel<DataSheetViewModel>() {
        parametersOf(factionId, factionName)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        CenteredTopBar(
            title = viewModel.factionNameState,
            navigationAction = { navigator.popBackStack() })
        LazyColumn {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = md_theme_dark_onPrimary)
                        .padding(all = 5.dp),
                    text = stringResource(R.string.regular_datasheets),
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            viewModel.datasheetList.normalList.forEach {
                item {
                    DatasheetItem(datasheet = it) { id ->
                        navigator.navigate(UnitPageDestination(id))
                    }
                }
            }
            if (viewModel.datasheetList.patrolList.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = md_theme_dark_onPrimary)
                            .padding(all = 5.dp),
                        text = stringResource(R.string.combat_patrol_datasheets),
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
            viewModel.datasheetList.patrolList.forEach {
                item {
                    DatasheetItem(datasheet = it) { id ->
                        navigator.navigate(UnitPageDestination(id))
                    }
                }
            }
        }
    }
}