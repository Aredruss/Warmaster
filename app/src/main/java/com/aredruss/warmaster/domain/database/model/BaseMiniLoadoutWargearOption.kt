package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class BaseMiniLoadoutWargearOption(
    @PrimaryKey val id: String,
    val count: Long,
    val wargearOptionId: String,
    val baseMiniatureLoadoutId: String
)
