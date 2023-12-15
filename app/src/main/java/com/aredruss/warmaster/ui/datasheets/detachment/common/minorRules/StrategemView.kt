package com.aredruss.warmaster.ui.datasheets.detachment.common.minorRules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.Strategem
import com.aredruss.warmaster.ui.common.CollapsableIsland
import com.aredruss.warmaster.ui.theme.md_theme_dark_errorContainer
import com.aredruss.warmaster.ui.theme.md_theme_dark_primaryContainer
import com.aredruss.warmaster.ui.theme.md_theme_dark_tertiaryContainer


@Composable
fun StrategemView(strategem: Strategem) {

    val typeTitleId = when (strategem.category) {
        "strategicPloy" -> stringResource(R.string.strategic_ploy)
        else -> stringResource(R.string.battle_tactic)
    }

    val titleBgColor = when (strategem.key) {
        "eitherPlayer" -> md_theme_dark_primaryContainer
        "opponentsTurn" -> md_theme_dark_errorContainer
        else -> md_theme_dark_tertiaryContainer
    }


    CollapsableIsland(
        modifier = Modifier.fillMaxWidth(),
        title = strategem.name,
        minorLeftText = "${(strategem.cpCost)} P",
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            ) {
                Text(
                    text = typeTitleId,
                    color = Color.Black.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(height = 10.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = stringResource(R.string.when_strategem),
                        color = titleBgColor,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 7.dp),
                        text = strategem.whenRules,
                        color = Color.Black.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                Spacer(modifier = Modifier.height(height = 10.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = stringResource(R.string.target_strategem),
                        color = titleBgColor,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = strategem.targetRules,
                        color = Color.Black.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(height = 10.dp))
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    Text(
                        text = stringResource(R.string.effect_strategem),
                        color = titleBgColor,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 5.dp),
                        text = strategem.effectRules,
                        color = Color.Black.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(height = 15.dp))
                strategem.restrictionRules?.let {
                    Row(
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "Rules: ",
                            color = titleBgColor,
                            style = MaterialTheme.typography.labelMedium,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = it,
                            color = Color.Black,
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Justify,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(height = 5.dp))
            }
        },
        titleBackground = titleBgColor
    )
}