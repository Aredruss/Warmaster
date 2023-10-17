package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DatasheetAbility(
    val id: String,
    val name: String,
    val abilityType: String,
    val rules: String? = null,
    val lore: String? = null,
    val armyRuleId: String? = null,
    val isAura: Boolean,
    val isBondsman: Boolean,
    val isPsychic: Boolean,
)