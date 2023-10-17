package com.aredruss.warmaster.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.aredruss.warmaster.ui.factions.FactionListViewModel
import com.aredruss.warmaster.ui.main.MainViewModel
import com.aredruss.warmaster.ui.datasheets.DataSheetViewModel
import com.aredruss.warmaster.ui.unit.UnitPageViewModel
import com.aredruss.warmaster.ui.about.AboutViewModel
import com.aredruss.warmaster.ui.subfaction.SubfactionViewModel

val uiModule = module {
    viewModelOf(::FactionListViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::DataSheetViewModel)
    viewModelOf(::UnitPageViewModel)
    viewModelOf(::AboutViewModel)
    viewModelOf(::SubfactionViewModel)
}