package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearOptionGroup(
    @PrimaryKey val id: String,
    val instructionText: String,
    val displayOrder: Long,
    val isStaticWargear: Boolean,
    val datasheetId: String,
    val miniatureId: String? = null,
)
