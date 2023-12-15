package com.aredruss.warmaster.domain.database.model.builder

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Army(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val detachmentId: String,
    val factionId: String,
    val pointLimit: Long
)
