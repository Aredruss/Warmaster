package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Miniature(
    val id: String,
    val name: String,
    val movement: String,
    val toughness: String,
    val save: String,
    val wounds: String,
    val leadership: String,
    val objectiveControl: String,
    val miniatureSlots: Long,
    val displayOrder: Long,
    val statlineHidden: Boolean,
    val isIndividualModels: Boolean,
    val excludedFromEnhancements: Boolean,
    val cannotBeWarlord: Boolean,
    val canBeNonCharacterWarlord: Boolean,
    val isSupremeCommander: Boolean,
    val datasheetId: String,
)