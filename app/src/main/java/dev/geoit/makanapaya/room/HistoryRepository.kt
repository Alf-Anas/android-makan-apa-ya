package dev.geoit.makanapaya.room

import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    /**
     * Retrieve all the history from the the given data source.
     */
    fun getAllHistoryStream(): Flow<List<HistoryEntity>>

    /**
     * Insert history in the data source
     */
    suspend fun insertHistory(history: HistoryEntity)

    /**
     * Delete history from the data source
     */
    suspend fun deleteHistory(history: HistoryEntity)

    /**
     * Update history in the data source
     */
    suspend fun updateHistory(history: HistoryEntity)
}
