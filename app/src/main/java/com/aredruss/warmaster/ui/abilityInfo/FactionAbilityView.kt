package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent
import com.aredruss.warmaster.ui.theme.md_theme_dark_onPrimary

@Composable
fun FactionAbilityView(
    modifier: Modifier, rules: List<RuleContainerComponent>
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                text = stringResource(R.string.faction_ability).uppercase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium
            )
        }
        items(rules) { rule ->
            when (rule.type) {
                "header", "subheader" -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        text = rule.textContent,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                "loreAccordion" -> {

                }

                else -> {
                    Column(
                        modifier = modifier
                            .padding(all = 5.dp)
                            .clip(shape = MaterialTheme.shapes.medium)
                            .border(
                                width = 5.dp,
                                color = md_theme_dark_onPrimary,
                                shape = MaterialTheme.shapes.medium
                            )
                            .background(color = MaterialTheme.colorScheme.secondary)
                            .padding(all = 5.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp, vertical = 10.dp),
                            text = rule.textContent,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colorScheme.onSecondary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}