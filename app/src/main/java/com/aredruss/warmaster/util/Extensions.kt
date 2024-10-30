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

fun String.ellipsizeEnd(count: Int): String {
    return if (this.length > count) this.take(16) + "..." else this
}