package com.aredruss.warmaster.di

import androidx.room.Room
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.aredruss.warmaster.domain.InfoRepository
import com.aredruss.warmaster.domain.FactionRepository
import com.aredruss.warmaster.domain.DatasheetRepository
import com.aredruss.warmaster.domain.UnitInfoRepository
import com.aredruss.warmaster.domain.WarmasterPrefs
import com.aredruss.warmaster.domain.database.WarmasterDatabase

val dataModule = module {
    single {
        Room.databaseBuilder(get(), WarmasterDatabase::class.java, "warmaster_db").build()
    }
    single { get<WarmasterDatabase>().datasheetDao() }
    single { get<WarmasterDatabase>().miniatureDao() }
    single { get<WarmasterDatabase>().factionKeywordDao() }
    single { get<WarmasterDatabase>().datasheetFactionKeywordDao() }
    single { get<WarmasterDatabase>().datasheetRuleDao() }
    single { get<WarmasterDatabase>().datasheetAbilityDao() }
    single { get<WarmasterDatabase>().datasheetRuleDao() }
    single { get<WarmasterDatabase>().unitCompositionDao() }
    single { get<WarmasterDatabase>().unitCompositionMiniatureDao() }
    single { get<WarmasterDatabase>().invSaveDao() }
    single { get<WarmasterDatabase>().datasheetAbilityBondDao() }
    single { get<WarmasterDatabase>().keywordsDao() }
    single { get<WarmasterDatabase>().miniatureKeywordsDao() }

    singleOf(::WarmasterPrefs)
    singleOf(::InfoRepository)
    singleOf(::FactionRepository)
    singleOf(::DatasheetRepository)
    singleOf(::UnitInfoRepository)
}
