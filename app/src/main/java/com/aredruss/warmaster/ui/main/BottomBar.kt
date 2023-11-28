package com.aredruss.warmaster.ui.main

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aredruss.warmaster.ui.NavGraphs
import com.aredruss.warmaster.ui.appCurrentDestinationAsState
import com.aredruss.warmaster.ui.destinations.ArmyListDestination
import com.aredruss.warmaster.ui.destinations.Destination
import com.aredruss.warmaster.ui.destinations.FactionListDestination
import com.aredruss.warmaster.ui.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import timber.log.Timber

val bottomBarApprovedDestinations = listOf(
    FactionListDestination, ArmyListDestination
)

@Composable
fun BottomBar(
    navController: NavController,
) {
    val currentDestination: Destination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    fun navigateProperly(destination: BottomBarAppDestination) {
        navController.navigate(destination.direction) {
            launchSingleTop = true
            restoreState = true
        }
    }

    if (currentDestination in bottomBarApprovedDestinations) {
        BottomAppBar(
            modifier = Modifier.height(height = 60.dp),
        ) {
            BottomBarAppDestination.values().forEach { destination ->
                val destinationList =
                    navController.currentBackStack.value.map { it.destination.route }
                val lastBottomBarDestination = navController.currentBackStack.value.findLast {
                    it.destination.route in destinationList
                }?.destination?.route

                val isSelected =
                    currentDestination == destination.direction ||
                            destination.direction.route == lastBottomBarDestination

                val color = if (isSelected) {
                    MaterialTheme.colorScheme.onBackground
                } else {
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                }
                Tab(modifier = Modifier
                    .height(height = 60.dp)
                    .weight(weight = 1f),
                    selected = isSelected,
                    onClick = {
                        navController.saveState()
                        if (destinationList.isEmpty()) {
                            navigateProperly(destination)
                        } else {
                            if (destinationList.last() in pages && destinationList.size > 2 && destination.direction.route != destinationList.last()
                                    .toString()
                            ) {
                                navController.popBackStack()
                            } else {
                                navigateProperly(destination)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = destination.icon),
                            contentDescription = stringResource(id = destination.label),
                            tint = color
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = destination.label), color = color
                        )
                    })
            }
        }
    }
}