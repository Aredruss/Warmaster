package com.aredruss.warmaster.domain.database.index

data class IndexedComposition(
    val miniatures: List<String>,
    val order: Long,
    val unitCompositionMiniature: List<String>,
    val unitComposition: IndexedUnitComposition
)

data class IndexedUnitComposition(
    val pointCosts: Long = 0,
    val isDefault: Boolean = true
)