package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DatasheetAbilityBond(
    val id: String,
    val restriction: String? = null,
    val displayOrder: Long,
    val datasheetId: String,
    val datasheetAbilityId: String,
)