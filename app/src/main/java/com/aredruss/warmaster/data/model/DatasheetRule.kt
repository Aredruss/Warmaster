package com.aredruss.warmaster.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DatasheetRule(
    val id: String,
    val name: String,
    val rules: String,
    val displayOrder: Long,
    val datasheetId: String
) {
    fun getFormattedRule() = if (rules.contains("■")) {
        rules
            .replace("■", "•")
            .replace("\n\n", "\n")
    } else {
        rules
    }
}