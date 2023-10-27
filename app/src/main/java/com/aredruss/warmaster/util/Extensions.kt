package com.aredruss.warmaster.util

fun String.getFormattedRule() = if (this.contains("■")) {
    this
        .replace("■", "•")
        .replace("\n\n", "\n")
} else {
    this
}