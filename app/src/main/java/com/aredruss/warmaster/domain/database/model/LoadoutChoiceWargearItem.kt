package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class LoadoutChoiceWargearItem(
    @PrimaryKey val id: String,
    val loadoutChoiceId: String,
    val wargearItemId: String,
    val count: Long,
)
