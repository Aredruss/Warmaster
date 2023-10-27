package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class LoadoutChoiceSet(
    @PrimaryKey val id: String,
    val miniatureId: String,
    val datasheetId: String,
    val limit: Long,
    val allowDuplicates: Boolean,
    val alternate: Boolean
)
