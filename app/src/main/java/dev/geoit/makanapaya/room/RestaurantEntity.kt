package dev.geoit.makanapaya.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name: String,
    val openHour: Int,
    val closeHour: Int,
    val menu: String,
    val createdAt: String,
)