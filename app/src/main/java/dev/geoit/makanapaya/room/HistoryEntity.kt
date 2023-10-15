package dev.geoit.makanapaya.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val generatedAt: String,
    val recommendations: String,
)