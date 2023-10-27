package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class WargearItem(
    @PrimaryKey val id: String,
    val name: String,
    val wargearType: String,
    val ruleText: String? = null,
)
