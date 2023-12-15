package com.aredruss.warmaster.domain.database.model.builder

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(
//    foreignKeys = [
//        ForeignKey(
//        entity = Army::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("armyId"),
//        onDelete = ForeignKey.CASCADE
//    )]
//)
data class ArmyUnit(
//    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val armyId: Long,
    val pointCost: Long,
    val datasheetId: String,
    val equipment: String? = null
)