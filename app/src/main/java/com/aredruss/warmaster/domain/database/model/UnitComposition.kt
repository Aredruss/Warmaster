package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UnitComposition(
    @PrimaryKey val id: String,
    val isDefault: Boolean,
    val points: Long,
    val displayOrder: Long,
    val datasheetId: String,
)
