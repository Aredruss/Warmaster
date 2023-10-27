package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DatasheetAbility(
    @PrimaryKey val id: String,
    val name: String,
    val abilityType: String,
    val rules: String? = null,
    val lore: String? = null,
    val armyRuleId: String? = null,
    val isAura: Boolean,
    val isBondsman: Boolean,
    val isPsychic: Boolean,
)