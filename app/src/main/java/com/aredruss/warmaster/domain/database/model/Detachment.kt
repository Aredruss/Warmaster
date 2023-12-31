package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Detachment(
    @PrimaryKey val id: String,
    val name: String,
    val displayOrder: Long,
    val publicationId: String,
    val bannerImage: String? = null,
    val rowImage: String? = null
) {
    @Ignore
    var bullets: List<DetachmentDetailBulletPoint> = emptyList()
}
