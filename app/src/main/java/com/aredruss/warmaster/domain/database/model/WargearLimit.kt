package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearLimit(
    @PrimaryKey val id: String,
    val limitedWargearChoiceSetId: String,
    val modelCount: Long,
    val choiceLimit: Long,
    val duplicateLimit: String? = null
)