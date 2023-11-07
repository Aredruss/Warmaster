package com.aredruss.warmaster.util

fun String.getFormattedRule() = if (this.contains("■")) {
    this
        .replace("■", "•")
        .replace("\n\n", "\n")
} else {
    this
}

fun String.uppercaseFirst(): String {
    return this.replaceFirst(
        this.first(), this.first().titlecaseChar()
    )
}