package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class KeywordRestrictionGroupKeyword(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val keywordRestrictionGroupId: String,
    val keywordId: String
)