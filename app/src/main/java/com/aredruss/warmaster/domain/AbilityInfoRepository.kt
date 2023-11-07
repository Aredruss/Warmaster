package com.aredruss.warmaster.domain

import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.domain.database.model.WargearAbility
import com.aredruss.warmaster.domain.database.model.WargearItemProfile
import timber.log.Timber

class AbilityInfoRepository {

    private var factionAbilities: List<Pair<DatasheetAbility, List<RuleContainerComponent>>>? = null
    private var wargearAbilities: List<WargearAbility>? = null
    private var datasheetAbility: List<DatasheetAbility>? = null

    fun setFactionAbilities(abilities: List<Pair<DatasheetAbility, List<RuleContainerComponent>>>) {
        factionAbilities = abilities
    }

    fun setGearAbilities(abilities: List<WargearItemProfile>) {
        wargearAbilities = abilities.map { it.wargearAbilities }.flatten().toList()
    }

    fun setDatasheetAbilities(abilities: List<DatasheetAbility>) {
        datasheetAbility = abilities
    }

    fun getFactionAbility(abilityId: String) = factionAbilities?.find {
        it.first.id == abilityId
    }?.second

    fun getWargearAbility(abilityId: String) = wargearAbilities?.find { it.id == abilityId }

    fun getDatasheetAbility(abilityId: String) =
        datasheetAbility?.find { it.id == abilityId }

}