package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Amendment(
    @PrimaryKey val id: String,
    val title: String,
    val text: String,
    val displayOrder: Int,
    val publicationId: String,
    val note: String?
)
