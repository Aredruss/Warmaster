package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class RuleSection(
    @PrimaryKey val id: String,
    val name: String,
    val displayOrder: Long,
    val parentId: String,
    val publicationId: String? = null
)
