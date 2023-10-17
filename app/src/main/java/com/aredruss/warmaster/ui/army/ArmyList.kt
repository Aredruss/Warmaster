package com.aredruss.warmaster.ui.army

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ArmyList(navigator: DestinationsNavigator) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Coming Soon")
    }
}