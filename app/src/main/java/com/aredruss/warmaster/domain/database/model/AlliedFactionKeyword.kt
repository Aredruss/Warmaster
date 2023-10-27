package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AlliedFactionKeyword(
    @PrimaryKey val id: String,
    val keywordId: String,
    val alliedFactionId: String,
    val battleSize: String,
    val limitCount: Long,
    val requiredWarlordMiniatureId: String? = null
)
