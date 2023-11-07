package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearItemProfile(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val range: String,
    val attacks: String,
    val ballisticSkill: String? = null,
    val weaponSkill: String? = null,
    val strength: String,
    val armourPenetration: String,
    val damage: String,
    val displayOrder: Long,
    val wargearItemId: String
) {
    @Ignore
    var wargearItemName: String? = null
    @Ignore
    var wargearItemRule: String? = null
    @Ignore
    var wargearAbilities: List<WargearAbility> = emptyList()
}
