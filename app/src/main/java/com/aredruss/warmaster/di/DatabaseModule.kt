package com.aredruss.warmaster.di

import androidx.room.Room
import com.aredruss.warmaster.domain.database.WarmasterDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), WarmasterDatabase::class.java, "warmaster_db").build()
    }
    single { get<WarmasterDatabase>().favoriteUnitDao() }

    single { get<WarmasterDatabase>().datasheetDao() }
    single { get<WarmasterDatabase>().miniatureDao() }

    single { get<WarmasterDatabase>().factionKeywordDao() }
    single { get<WarmasterDatabase>().datasheetFactionKeywordDao() }

    single { get<WarmasterDatabase>().datasheetRuleDao() }
    single { get<WarmasterDatabase>().datasheetAbilityBondDao() }
    single { get<WarmasterDatabase>().datasheetAbilityDao() }
    single { get<WarmasterDatabase>().datasheetRuleDao() }
    single { get<WarmasterDatabase>().datasheetSubAbilityDao() }
    single { get<WarmasterDatabase>().invSaveDao() }

    single { get<WarmasterDatabase>().unitCompositionDao() }
    single { get<WarmasterDatabase>().unitCompositionMiniatureDao() }

    single { get<WarmasterDatabase>().keywordsDao() }
    single { get<WarmasterDatabase>().miniatureKeywordsDao() }

    single { get<WarmasterDatabase>().wargearOptionDao() }
    single { get<WarmasterDatabase>().wargearOptionGroupDao() }

    single { get<WarmasterDatabase>().wargearItemDao() }
    single { get<WarmasterDatabase>().wargearItemProfileDao() }
    single { get<WarmasterDatabase>().wargearItemProfileAbilityDao() }
    single { get<WarmasterDatabase>().wargearAbilityDao() }
    single { get<WarmasterDatabase>().wargearRuleDao() }

    single { get<WarmasterDatabase>().ruleContainerComponentComponent() }

    single { get<WarmasterDatabase>().loadoutChoiceDao() }
    single { get<WarmasterDatabase>().loadoutChoiceSetDao() }
    single { get<WarmasterDatabase>().loadoutChoiceWargearItemDao() }
}