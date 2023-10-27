package com.aredruss.warmaster.ui.splash

import android.widget.ProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.destinations.FactionListDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<SplashScreenViewModel>()

    viewModel.allowNavigateNext?.consume()?.let { isLoaded ->
        if (isLoaded) {
            navigator.popBackStack()
            navigator.navigate(FactionListDestination)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator()
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = stringResource(R.string.splash_message),
            style = MaterialTheme.typography.labelLarge
        )
    }
}