package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

// connects factions to datasheets
@Serializable
data class DatasheetFactionKeyword(
    val datasheetId: String,
    val factionKeywordId: String,
    val displayOrder: Long,
)