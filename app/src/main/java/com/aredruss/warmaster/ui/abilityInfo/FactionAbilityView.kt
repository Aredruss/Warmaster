package com.aredruss.warmaster.ui.abilityInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.BulletPoint
import com.aredruss.warmaster.domain.database.model.RuleContainerComponent

@Composable
fun FactionAbilityView(
    modifier: Modifier,
    shouldShowFactionHeader: Boolean = true,
    rules: List<RuleContainerComponent>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color.White)
            .padding(all = 5.dp)
    ) {
        if (shouldShowFactionHeader) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                text = stringResource(R.string.faction_ability).uppercase(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium
            )
        }
        rules.forEach { rule ->
            when (rule.type) {
                "header" -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        text = rule.textContent,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                "subheader" -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 5.dp),
                        text = rule.textContent,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.Black.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                "image" -> {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        model = rule.imageUrl,
                        alignment = Alignment.Center,
                        contentDescription = ""
                    )
                }

                "bullets" -> {
                    BulletView(rule.bullets)
                }

                "loreAccordion" -> {

                }

                else -> {
                    val textStyle = when (rule.type) {
                        "textBold" -> FontWeight.Bold
                        "quote" -> FontWeight.Light
                        else -> FontWeight.Normal
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp, vertical = 10.dp),
                        text = rule.textContent,
                        fontWeight = textStyle,
                        textAlign = TextAlign.Justify,
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun BulletView(bullets: List<BulletPoint>) {
    bullets.forEach {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = (5 + it.indent.toInt()).dp, vertical = 2.dp),
            text = "• " + it.text,
            textAlign = TextAlign.Justify,
            color = Color.Black.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelMedium
        )
    }
}