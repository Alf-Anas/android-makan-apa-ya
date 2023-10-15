package dev.geoit.makanapaya.room

import kotlinx.coroutines.flow.Flow

class OfflineHistoryRepository(private val historyDao: HistoryDao) : HistoryRepository {
    override fun getAllHistoryStream(): Flow<List<HistoryEntity>> = historyDao.getAllHistory()

    override suspend fun insertHistory(history: HistoryEntity) = historyDao.insert(history)

    override suspend fun deleteHistory(history: HistoryEntity) = historyDao.delete(history)

    override suspend fun updateHistory(history: HistoryEntity) = historyDao.update(history)
}
