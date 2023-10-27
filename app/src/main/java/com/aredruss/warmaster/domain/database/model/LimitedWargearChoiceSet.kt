package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class LimitedWargearChoiceSet(
    @PrimaryKey val id: String,
    val miniatureId: String? = null,
    val datasheetId: String,
    val mandatory: Boolean
)
