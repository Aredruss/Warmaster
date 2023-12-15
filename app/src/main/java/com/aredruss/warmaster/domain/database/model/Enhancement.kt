package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Enhancement(
    @PrimaryKey val id: String,
    val name: String,
    val lore: String,
    val rules: String,
    val detachmentId: String?,
    val basePointsCost: Long?,
    val displayOrder: Long,
    val isCombatPatrol: Boolean,
    val publicationId: String
)
