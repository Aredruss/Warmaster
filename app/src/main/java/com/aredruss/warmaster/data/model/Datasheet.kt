package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Datasheet(
    val id: String,
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