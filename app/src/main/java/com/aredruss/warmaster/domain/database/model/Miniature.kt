package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Miniature(
    @PrimaryKey val id: String,
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