package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
class WargearRule(
    @PrimaryKey val id: String,
    val rulesText: String,
    val displayOrder: Long,
    val datasheetId: String
)