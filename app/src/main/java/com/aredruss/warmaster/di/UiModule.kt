package com.aredruss.warmaster.di

import com.aredruss.warmaster.ui.abilityInfo.AbilityInfoViewModel
import com.aredruss.warmaster.ui.about.AboutViewModel
import com.aredruss.warmaster.ui.army.ArmyListViewModel
import com.aredruss.warmaster.ui.datasheets.DataSheetViewModel
import com.aredruss.warmaster.ui.datasheets.PublicationDatasheetsViewModel
import com.aredruss.warmaster.ui.datasheets.allFavorites.SavedDatasheetsViewModel
import com.aredruss.warmaster.ui.datasheets.armyRules.ArmyRulesViewModel
import com.aredruss.warmaster.ui.datasheets.detachment.DetachmentViewModel
import com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules.MinorRulesViewModel
import com.aredruss.warmaster.ui.datasheets.index.IndexScreenViewModel
import com.aredruss.warmaster.ui.datasheets.menu.GameMenuViewModel
import com.aredruss.warmaster.ui.datasheets.patrol.PatrolViewModel
import com.aredruss.warmaster.ui.datasheets.search.SearchViewModel
import com.aredruss.warmaster.ui.factions.FactionListViewModel
import com.aredruss.warmaster.ui.rules.containers.RuleContainersViewModel
import com.aredruss.warmaster.ui.rules.sections.RuleSectionListViewModel
import com.aredruss.warmaster.ui.splash.SplashScreenViewModel
import com.aredruss.warmaster.ui.unit.UnitPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::FactionListViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::UnitPageViewModel)
    viewModelOf(::AboutViewModel)
    viewModelOf(::DataSheetViewModel)
    viewModelOf(::IndexScreenViewModel)
    viewModelOf(::SavedDatasheetsViewModel)
    viewModelOf(::AbilityInfoViewModel)
    viewModelOf(::ArmyListViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::DetachmentViewModel)
    viewModelOf(::PatrolViewModel)
    viewModelOf(::GameMenuViewModel)
    viewModelOf(::PublicationDatasheetsViewModel)
    viewModelOf(::ArmyRulesViewModel)
    viewModelOf(::MinorRulesViewModel)
    viewModelOf(::RuleSectionListViewModel)
    viewModelOf(::RuleContainersViewModel)
}