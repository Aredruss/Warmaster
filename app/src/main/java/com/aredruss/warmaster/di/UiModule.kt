package com.aredruss.warmaster.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.aredruss.warmaster.ui.factions.FactionListViewModel
import com.aredruss.warmaster.ui.main.MainViewModel

val uiModule = module {
    viewModelOf(::FactionListViewModel)
    viewModelOf(::MainViewModel)
}