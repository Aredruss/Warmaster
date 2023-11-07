package com.aredruss.warmaster.di

import com.aredruss.warmaster.domain.AbilityInfoRepository
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.domain.FavoriteUnitRepository
import com.aredruss.warmaster.domain.InfoRepository
import com.aredruss.warmaster.domain.LoadoutChoiceRepository
import com.aredruss.warmaster.domain.UnitCompositionRepository
import com.aredruss.warmaster.domain.UnitInfoRepository
import com.aredruss.warmaster.domain.WargearRepository
import com.aredruss.warmaster.domain.WarmasterPrefs
import com.aredruss.warmaster.domain.populators.CompositionPopulator
import com.aredruss.warmaster.domain.populators.DatasheetPopulator
import com.aredruss.warmaster.domain.populators.FactionPopulator
import com.aredruss.warmaster.domain.populators.LoadoutPopulator
import com.aredruss.warmaster.domain.populators.WargearPopulator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CompositionPopulator)
    singleOf(::WargearPopulator)
    singleOf(::DatasheetPopulator)
    singleOf(::LoadoutPopulator)
    singleOf(::FactionPopulator)

    singleOf(::WarmasterPrefs)
    singleOf(::InfoRepository)
    singleOf(::LoadoutChoiceRepository)
    singleOf(::FactionRepository)
    singleOf(::AbilityInfoRepository)
    singleOf(::DatasheetRepository)
    singleOf(::UnitInfoRepository)
    singleOf(::UnitCompositionRepository)
    singleOf(::WargearRepository)
    singleOf(::FavoriteUnitRepository)
}
