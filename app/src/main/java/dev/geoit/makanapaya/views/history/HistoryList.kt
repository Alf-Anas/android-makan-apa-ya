package dev.geoit.makanapaya.views.history

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.History

@Composable
fun HistoryList(historyList: List<History>, modifier: Modifier = Modifier) {
    if (historyList.isEmpty()) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "No history found!",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            )
        }
    }
    LazyColumn(modifier = modifier) {
        items(historyList) { history ->
            HistoryCard(
                history = history,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}