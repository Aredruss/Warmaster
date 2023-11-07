package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.WargearRule
import com.aredruss.warmaster.ui.common.CollapsableContainer


@Composable
fun WargearOptionView(
    modifier: Modifier = Modifier,
    rules: List<WargearRule>
) {
    CollapsableContainer(
        title = stringResource(R.string.wargear_options),
        modifier = modifier
    ) {
        rules.forEach { rule ->
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(top = 5.dp),
                    text = rule.rulesText,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(height = 5.dp))
    }
}
