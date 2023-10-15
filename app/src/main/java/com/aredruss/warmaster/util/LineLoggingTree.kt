package com.aredruss.warmaster.util

import timber.log.Timber

class LineLoggingTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return String.format(
            "[L:%s] [M:%s] [C:%s]",
            element.lineNumber,
            element.methodName,
            super.createStackElementTag(element)
        )
    }
}