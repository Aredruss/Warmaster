package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
class AllegianceAbility(
   @PrimaryKey val id: String,
    val name: String,
    val rules: String,
    val displayOrder: Long,
    val requiresWargearItemId: String? = null,
    val allegianceAbilityGroupId: String
)