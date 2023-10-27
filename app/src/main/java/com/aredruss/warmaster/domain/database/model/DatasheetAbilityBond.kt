package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DatasheetAbilityBond(
    @PrimaryKey val id: String,
    val restriction: String? = null,
    val displayOrder: Long,
    val datasheetId: String,
    val datasheetAbilityId: String,
)