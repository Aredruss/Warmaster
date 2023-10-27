package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AlliedFaction(
    @PrimaryKey val id: String,
    val requiredWarlordMiniatureId: String? = null,
    val incursionPointsLimit: Long,
    val strikeForcePointsLimit: Long,
    val onslaughtPointsLimit: Long,
    val canTakeEnhancements: Boolean,
    val isMutuallyExclusiveKeywordLimit: Boolean
)
