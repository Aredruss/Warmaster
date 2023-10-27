package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearOption(
    @PrimaryKey val id: String,
    val inputType: String,
    val defaultValue: Long,
    val displayOrder: Long,
    val wargearItemId: String,
    val wargearOptionGroupId: String
)