package com.aredruss.warmaster.domain.database.model.builder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArmyUnit(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val armyId: Long,
    val pointCost: Long,
    val datasheetId: String,
    val equipment: String? = null
)