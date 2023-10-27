package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Faq(
    @PrimaryKey val id: String,
    val question: String,
    val answer: String,
    val displayOrder: Long,
    val publicationId: String
)
