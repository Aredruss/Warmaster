package com.aredruss.warmaster.ui.datasheets.detachment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aredruss.warmaster.R
import com.aredruss.warmaster.domain.database.model.Detachment

@Composable
fun DetachmentItem(detachment: Detachment, action: (String, String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp)
            .clip(shape = ShapeDefaults.Small)
            .clickable {
                action.invoke(detachment.id, detachment.name)
            }
            .background(color = Color.White)
            .fillMaxWidth()
            .height(height = 50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(weight = 9f),
            text = detachment.name,
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall
        )
        Icon(
            modifier = Modifier.weight(weight = 1f),
            tint = Color.Black.copy(alpha = 0.7f),
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
        )
    }
}