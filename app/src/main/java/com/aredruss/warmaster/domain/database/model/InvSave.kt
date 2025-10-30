package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class InvSave (
    @PrimaryKey val id: String,
    val save: String = "",
    val rules: String? = null,
    val datasheetId: String,
    val miniatureId: String? = null,
)