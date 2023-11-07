package com.aredruss.warmaster.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.index.IndexedComposition
import com.aredruss.warmaster.ui.common.CollapsableContainer

@Composable
fun UnitCompositionView(
    modifier: Modifier,
    items: List<IndexedComposition>
) {
    CollapsableContainer(
        modifier = modifier,
        title = stringResource(R.string.unit_composition)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(top = 10.dp, start = 10.dp),
                text = stringResource(R.string.model),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp),
                text = stringResource(R.string.count),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp),
                text = stringResource(R.string.points),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium
            )
        }
        items.forEach { indexedComposition ->
            indexedComposition.miniatures.forEachIndexed { index, name ->
                Row(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier
                            .weight(2f)
                            .padding(top = 10.dp, start = 10.dp),
                        text = name,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        text = indexedComposition.unitCompositionMiniature[index],
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        text = if (index == 0) {
                            indexedComposition.unitComposition.pointCosts.toString()
                        } else " ",
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(height = 5.dp))
    }
}