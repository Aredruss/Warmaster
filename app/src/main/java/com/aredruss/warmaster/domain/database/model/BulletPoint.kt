package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class BulletPoint(
    @PrimaryKey val id: String,
    val text: String ?= null,
    val indent: Long ?= null,
    val displayOrder: Long ?= null,
    val ruleContainerComponentId: String ?= null
)
