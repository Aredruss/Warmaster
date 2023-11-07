package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.DatasheetRule
import com.aredruss.warmaster.ui.common.CollapsableContainer


@Composable
fun DatasheetRuleView(
    modifier: Modifier = Modifier,
    rules: List<DatasheetRule>
) {
    CollapsableContainer(
    title = "Unit rules",
    modifier = modifier
    ) {
        rules.forEach { rule ->
            Column(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    text = rule.name,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 10.dp),
                    text = rule.rules,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
