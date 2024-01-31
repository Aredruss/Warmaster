package com.aredruss.warmaster.domain.database.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class RuleContainer(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String? = null,
    val containerType: String,
    val displayOrder: Long,
    val ruleSectionId: String,
    val stratagemId: String? = null
) {
    @Ignore
    var childContainerComponents: List<RuleContainerComponent> = emptyList()

    @Ignore
    var childStrategem: Strategem? = null

    @Ignore
    var formattedTitle: String? = null
}