package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Datasheet(
    @PrimaryKey val id: String,
    val name: String,
    val maxModelCount: Int? = null,
    val publicationId: String? = null,
    val bannerImage: String? = null,
    val rowImage: String? = null,
    val isCombatPatrol: Boolean,
    val isSuccessorChapter: Boolean,
    val lore: String? = null,
    val displayOrder: Long,
    val allegianceAbilityGroupId: String? = null,
    val unitComposition: String? = null,
)