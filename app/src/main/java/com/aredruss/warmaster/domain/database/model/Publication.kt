package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Publication(
    @PrimaryKey val id: String,
    val name: String,
    val displayOrder: Long,
    val combatPatrolName: String? = null,
    val factionBackgroundImage: String,
    val factionKeywordId: String? = null,
    val isCoreRules: Boolean,
    val productId: String? = null,
    val errataDate: String? = null
)
