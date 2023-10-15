package dev.geoit.makanapaya.room

import android.content.Context


interface AppContainer {
    val historyRepository: HistoryRepository
    val restaurantRepository: RestaurantRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val historyRepository: HistoryRepository by lazy {
        OfflineHistoryRepository(MainDatabase.getDatabase(context).historyDao())
    }

    override val restaurantRepository: RestaurantRepository by lazy {
        OfflineRestaurantRepository(MainDatabase.getDatabase(context).restaurantDao())
    }
}
