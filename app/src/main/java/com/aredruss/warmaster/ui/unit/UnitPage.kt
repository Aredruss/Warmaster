package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.TextBlock
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@Destination
@Composable
fun UnitPage(
    datasheetId: String,
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<UnitPageViewModel>()

    viewModel.getInfoByDataSheetId(datasheetId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        CenteredTopBar(
            title = viewModel.datasheet?.name ?: "Unknown",
            navigationAction = { navigator.popBackStack() })
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = viewModel.datasheet?.bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        viewModel.miniatureList.forEach { mini ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = md_theme_dark_onPrimary)
                    .padding(all = 5.dp),
                text = mini.name,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            StatBlock(miniature = mini, invSave = viewModel.invSave)
        }

        viewModel.datasheetAbilities?.let {
            UnitAbilities(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 10.dp),
                abilities = it
            )
        }
        viewModel.ruleset?.let { list ->
            UnitRules(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 10.dp),
                rules = list
            )
        }
        viewModel.datasheet?.lore?.let {
            TextBlock(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 10.dp),
                textTitle = "Lore",
                textValue = it
            )
        }
        viewModel.datasheet?.unitComposition?.let {
            TextBlock(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(horizontal = 10.dp),
                textTitle = "Unit info",
                textValue = it
            )
        }
        viewModel.unitCompositions?.forEach {
            Text(text = it.toString(), color = MaterialTheme.colorScheme.onBackground)
        }
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        viewModel.unitCompositionsByMini?.forEach {
            Text(text = it.toString(), color = MaterialTheme.colorScheme.onBackground)
        }


        viewModel.keywords?.let { list ->
            UnitKeywords(
                modifier = Modifier.padding(top = 5.dp),
                unitKeywords = list
            )
        }
    }
}