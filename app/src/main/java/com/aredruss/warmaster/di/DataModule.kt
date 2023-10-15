package com.aredruss.warmaster.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.aredruss.warmaster.data.InfoRepository

val dataModule = module {
    singleOf(::InfoRepository)
}
