package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Faction(
    val id: String,
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
