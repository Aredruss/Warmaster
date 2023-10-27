package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DetachmentDetail(
    @PrimaryKey val id: String,
    val title: String,
    val displayOrder: Long,
    val detachmentId: String
)
