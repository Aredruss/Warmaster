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

@Composable
fun UnitCompositionView(
    modifier: Modifier,
    items: List<IndexedComposition>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            text = stringResource(R.string.unit_composition),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.labelSmall
        )
        items.forEach { indexedComposition ->
            indexedComposition.miniatures.forEachIndexed { index, name ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = Modifier
                            .weight(2f)
                            .padding(top = 10.dp, start = 10.dp),
                        text = name,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        text = indexedComposition.unitCompositionMiniature[index],
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        text =
                        if (index == 0) {
                            indexedComposition.unitComposition.pointCosts.toString()
                        } else " ",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.labelSmall
                    )

                }
            }
            Spacer(modifier = Modifier.height(height = 10.dp))
        }
    }
    Spacer(modifier = Modifier.height(height = 5.dp))
}