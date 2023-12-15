package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DetachmentRule(
    @PrimaryKey val id: String,
    val name: String,
    val displayOrder: Long,
    val detachmentId: String
) {
    @Ignore
    var rules: List<RuleContainerComponent> = emptyList()
}