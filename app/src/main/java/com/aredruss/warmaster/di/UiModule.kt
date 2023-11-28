package com.aredruss.warmaster.di

import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.about.AboutViewModel
import com.aredruss.warmaster.ui.army.ArmyListViewModel
import com.aredruss.warmaster.ui.datasheets.DataSheetViewModel
import com.aredruss.warmaster.ui.datasheets.allFavorites.SavedDatasheetsViewModel
import com.aredruss.warmaster.ui.datasheets.gameType.GameTypeViewModel
import com.aredruss.warmaster.ui.datasheets.search.SearchViewModel
import com.aredruss.warmaster.ui.factions.FactionListViewModel
import com.aredruss.warmaster.ui.splash.SplashScreenViewModel
import com.aredruss.warmaster.ui.subfaction.SubFactionViewModel
import com.aredruss.warmaster.ui.unit.UnitPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::FactionListViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::DataSheetViewModel)
    viewModelOf(::UnitPageViewModel)
    viewModelOf(::AboutViewModel)
    viewModelOf(::SubFactionViewModel)
    viewModelOf(::GameTypeViewModel)
    viewModelOf(::SavedDatasheetsViewModel)
    viewModelOf(::AbilityInfoViewModel)
    viewModelOf(::ArmyListViewModel)
    viewModelOf(::SearchViewModel)
}