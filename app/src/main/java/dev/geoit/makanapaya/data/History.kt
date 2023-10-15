package dev.geoit.makanapaya.data

import dev.geoit.makanapaya.room.Converters
import dev.geoit.makanapaya.room.HistoryEntity

data class History(
    val id: Int,
    val recommendations: List<String>,
    val generatedAt: String,
)

fun convertHistoryEntityListToHistoryList(historyEntityList: List<HistoryEntity>): List<History> {
    val converters = Converters()
    val historyEntries = mutableListOf<History>()
    for (historyEntity in historyEntityList) {
        historyEntries.add(
            History(
                id = historyEntity.id,
                recommendations = converters.restoreList(historyEntity.recommendations),
                generatedAt = historyEntity.generatedAt
            )
        )
    }
    return historyEntries
}