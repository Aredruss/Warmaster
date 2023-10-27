package com.aredruss.warmaster.util

class Event<T>(private val content: T) {
    private var consumed = false

    fun peek(): T = content

    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }
}