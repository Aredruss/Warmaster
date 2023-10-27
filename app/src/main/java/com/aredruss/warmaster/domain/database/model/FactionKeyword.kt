package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class FactionKeyword(
    @PrimaryKey val id: String,
    val name: String,
    val commonName: String? = null,
    val lore: String? = null,
    val excludedFromArmyBuilder: Boolean,
    val rosterHeaderImage: String? = null,
    val armySelectionImage: String? = null,
    val moreInfoImage: String? = null,
    val rosterFactionImage: String? = null,
    val parentFactionKeywordId: String? = null
)
