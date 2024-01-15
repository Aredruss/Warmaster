package com.aredruss.warmaster.ui.about

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.common.CenteredTopBar
import com.aredruss.warmaster.ui.theme.discordBlue
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AboutWarmaster(navigator: DestinationsNavigator) {

    val viewModel = getViewModel<AboutViewModel>()
    val url = "https://play.google.com/store/apps/details?id=com.aredruss.warmaster&hl=en_US"
    val githubUrl = "https://github.com/Aredruss/Warmaster"
    val discordUrl = "https://discord.com/invite/8fcDRVZQ8x"

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            CenteredTopBar(
                title = stringResource(id = R.string.about),
                navigationAction = {
                    navigator.popBackStack()
                })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = stringResource(id = R.string.share_app),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                if (BarcodeType.QR_CODE.isValueValid(url)) {
                    Barcode(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(size = 200.dp),
                        resolutionFactor = 10,
                        type = BarcodeType.QR_CODE,
                        value = url,
                        showProgress = true
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = stringResource(R.string.or_get_the_link),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.small)
                        .clickable {
                            clipboardManager.setText(AnnotatedString((url)))
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        modifier = Modifier
                            .clip(shape = MaterialTheme.shapes.small)
                            .border(
                                width = 2.dp,
                                color = md_theme_dark_onPrimary,
                                shape = MaterialTheme.shapes.small
                            )
                            .background(color = MaterialTheme.colorScheme.secondary)
                            .padding(all = 10.dp),
                        text = url.dropLast(40).plus("…"),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center,

                        )
                }
                Spacer(modifier = Modifier.height(height = 20.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = stringResource(R.string.and_join_the_official_discord),
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(size = 60.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .background(color = discordBlue)
                            .clickable {
                                uriHandler.openUri(discordUrl)
                            }
                            .padding(all = 10.dp),
                        painter = painterResource(id = R.drawable.ic_discord),
                        contentDescription = "",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(width = 15.dp))
                    Icon(
                        modifier = Modifier
                            .size(size = 60.dp)
                            .clip(shape = MaterialTheme.shapes.small)
                            .background(color = Color.White)
                            .clickable {
                                uriHandler.openUri(githubUrl)
                            }
                            .padding(all = 10.dp),
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }
            }
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    text = "Data version: ${viewModel.aboutInfo}",
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}