package dev.geoit.makanapaya.views.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.geoit.makanapaya.LocalHistoryRepository
import dev.geoit.makanapaya.data.History
import dev.geoit.makanapaya.data.convertHistoryEntityListToHistoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HistoryScreen() {
    val historyRepository = LocalHistoryRepository.current
    var historyList by remember { mutableStateOf<List<History>>(emptyList()) }

    LaunchedEffect(historyRepository) {
        withContext(Dispatchers.IO) {
            historyRepository.getAllHistoryStream().collect {
                historyList = convertHistoryEntityListToHistoryList(it)
            }
        }
    }

    HistoryList(
        historyList = historyList,
    )
}