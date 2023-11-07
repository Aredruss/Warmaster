package com.aredruss.warmaster.domain.database.index

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteUnit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val datasheetId: String,
    val factionId: String
)