package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Strategem(
    @PrimaryKey val id: String,
    val name: String,
    val lore: String,
    val whenRules: String,
    val targetRules: String,
    val effectRules: String,
    val restrictionRules: String ?= null,
    val key: String,
    val category: String,
    val cpCost: String,
    val displayOrder: Long,
    val publicationId: String,
    val detachmentId: String ?= null
)
