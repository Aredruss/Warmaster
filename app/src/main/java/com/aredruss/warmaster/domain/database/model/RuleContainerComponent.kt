package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class RuleContainerComponent(
    @PrimaryKey val id: String,
    val textContent: String? = "",
    val displayOrder: Long,
    val type: String,
    val altText: String? = null,
    val imageUrl: String? = null,
    val trigger: String? = null,
    val effect: String? = null,
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

//{
//   "id": "05da79d8-b304-49af-be92-e3d3257acff7",
//   "textContent": "The Path of the Penitent",
//   "displayOrder": 3,
//   "type": "header",
//   "altText": null,
//   "imageUrl": null,
//   "title": null,
//   "subtitle": null,
//   "backgroundColor": null,
//   "trigger": null,
//   "effect": null,
//   "ruleContainerId": null,
//   "armyRuleId": null,
//   "detachmentRuleId": "b37890cc-d1f6-460f-ae40-745b6a738e9d"
//  },