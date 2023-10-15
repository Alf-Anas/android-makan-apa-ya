package dev.geoit.makanapaya.views.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.History
import dev.geoit.makanapaya.utils.getTimeAgo

@Composable
fun HistoryCard(history: History, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = getTimeAgo(history.generatedAt),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.End)
            )
            for (recommendation in history.recommendations) {
                Text(
                    text = recommendation,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
