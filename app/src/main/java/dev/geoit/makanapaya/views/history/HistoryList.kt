package dev.geoit.makanapaya.views.history

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.History

@Composable
fun HistoryList(historyList: List<History>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(historyList) { history ->
            HistoryCard(
                history = history,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}