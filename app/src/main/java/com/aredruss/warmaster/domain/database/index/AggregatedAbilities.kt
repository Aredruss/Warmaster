package com.aredruss.warmaster.domain.database.index

import com.aredruss.warmaster.domain.database.model.DatasheetAbility
import com.aredruss.warmaster.domain.database.model.DatasheetSubAbility
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent

data class AggregatedAbilities(
    val normalAbilities: Map<String, List<DatasheetAbility>> = emptyMap(),
    val subNormalAbilities: Map<String, List<DatasheetSubAbility>>? = null,
    val factionAbilities: List<Pair<DatasheetAbility, List<RuleContainerComponent>>>? = null
)
