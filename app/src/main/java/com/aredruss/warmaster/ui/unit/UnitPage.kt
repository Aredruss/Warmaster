package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.common.TextBlock
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

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

        viewModel.miniature?.let { mini ->
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
        viewModel.damageRuleset?.let { list ->
            UnitRules(
                modifier = Modifier
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
        viewModel.keywords?.let { list ->
            UnitKeywords(
                modifier = Modifier.padding(top = 5.dp),
                unitKeywords = list
            )
        }
    }
}