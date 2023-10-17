package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InvSave (
    val id: String,
    val save: String,
    val rules: String? = null,
    val datasheetId: String,
    val miniatureId: String? = null,
)