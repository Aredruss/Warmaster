package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class RuleContainerComponent(
    @PrimaryKey val id: String,
    val textContent: String,
    val displayOrder: Long,
    val type: String,
    val altText: String? = null,
    val imageUrl: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val backgroundColor: String? = null,
    val ruleContainerId: String? = null,
    val armyRuleId: String? = null,
    val detachmentRuleId: String? = null
) {
    @Ignore
    var bullets: List<BulletPoint> = emptyList()
}
