package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.domain.database.model.Amendment
import com.aredruss.warmaster.ui.common.TextBlock

@Composable
fun AmendmentsListView(
    faqs: List<Amendment>
) {
    faqs.forEach { item ->
        TextBlock(
            modifier = Modifier
                .padding(bottom = 8.dp),
            textTitle = item.title,
            textValue = item.text
        )
    }
}