package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DetachmentFactionKeyword(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val detachmentId: String,
    val factionKeywordId: String
)
