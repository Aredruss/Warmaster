package com.aredruss.warmaster.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aredruss.warmaster.R
import com.aredruss.warmaster.ui.destinations.ArmyListDestination
import com.aredruss.warmaster.ui.destinations.FactionListDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class BottomBarAppDestination(
    val direction: DirectionDestinationSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Factions(FactionListDestination, R.drawable.ic_booklet, R.string.factions),
    Armies(ArmyListDestination, R.drawable.ic_notepad, R.string.army_builder_tab_name),
}

val pages = listOf("army_list","faction_list")
