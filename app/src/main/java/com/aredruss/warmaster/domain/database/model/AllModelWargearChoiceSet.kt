package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
class AllModelWargearChoiceSet(
    @PrimaryKey val id: String,
    val miniatureId: String ?= null,
    val datasheetId: String
)