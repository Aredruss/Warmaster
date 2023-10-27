package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ConditionalKeyword(
    @PrimaryKey val id: String,
    val datasheetId: String,
    val keywordId: String,
    val requiredWarlordMiniatureId: String? = null,
    val requiredAllegianceAbilityId: String? = null
)
