package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class MiniatureKeyword(
    @PrimaryKey val id: String,
    val miniatureId: String,
    val keywordId: String,
    val displayOrder: Long
)