package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AllModelWargearChoice(
    @PrimaryKey val id: String,
    val allModelWargearChoiceSetId: String,
    val substitute: Boolean
)
