package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DatasheetDamageRule(
    val id: String,
    val name: String,
    val rules: String,
    val displayOrder: Long,
    val datasheetId: String,
)