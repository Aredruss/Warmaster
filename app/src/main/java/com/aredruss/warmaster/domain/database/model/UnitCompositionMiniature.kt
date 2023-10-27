package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class UnitCompositionMiniature(
    @PrimaryKey val id: String,
    val miniatureId: String,
    val min: Long,
    val max: Long,
    val unitCompositionId: String
)
