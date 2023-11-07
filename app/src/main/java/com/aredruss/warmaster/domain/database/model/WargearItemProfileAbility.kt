package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearItemProfileAbility(
    @PrimaryKey val id: String,
    val wargearItemProfileId: String,
    val wargearAbilityId: String,
    val displayOrder: Long
)
