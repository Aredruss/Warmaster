package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DetachmentDetailBulletPoint(
    @PrimaryKey val id: String,
    val text: String,
    val displayOrder: Long,
    val detachmentDetailId: String
)