package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class AllModelWargearChoiceWargearItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val allModelWargearChoiceId: String,
    val wargearItemId: String,
    val count: Long
)
