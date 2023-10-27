package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class KeywordRestrictionGroup(
    @PrimaryKey val id: String,
    val factionKeywordId: String,
    val limit: Long,
    val requiresWarlordMiniatureId: String? = null
)