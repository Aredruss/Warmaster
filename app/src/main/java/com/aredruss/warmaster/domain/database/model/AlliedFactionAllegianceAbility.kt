package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AlliedFactionAllegianceAbility(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val alliedFactionId: String,
    val allegianceAbilityId: String
)
