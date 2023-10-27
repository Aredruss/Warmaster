package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

// connects factions to datasheets
@Serializable
@Entity
data class DatasheetFactionKeyword(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val datasheetId: String,
    val factionKeywordId: String,
    val displayOrder: Long,
)