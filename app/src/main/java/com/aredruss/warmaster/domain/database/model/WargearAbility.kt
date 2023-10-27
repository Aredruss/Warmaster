package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearAbility(
    @PrimaryKey val id: String,
    val name: String,
    val lore: String? = null,
    val rules: String
)
