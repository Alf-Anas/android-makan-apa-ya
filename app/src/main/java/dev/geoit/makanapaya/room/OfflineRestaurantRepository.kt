package dev.geoit.makanapaya.room

import dev.geoit.makanapaya.utils.getCurrentHour
import kotlinx.coroutines.flow.Flow

class OfflineRestaurantRepository(private val restaurantDao: RestaurantDao) : RestaurantRepository {
    override fun getAllRestaurantStream(): Flow<List<RestaurantEntity>> =
        restaurantDao.getAllRestaurant()

    override fun getAllActiveRestaurantStream(): Flow<List<RestaurantEntity>> =
        restaurantDao.getAllActiveRestaurant(getCurrentHour())

    override fun getRestaurantByIdStream(id: Int): Flow<RestaurantEntity?> =
        restaurantDao.getRestaurantById(id)

    override suspend fun insertRestaurant(restaurantEntity: RestaurantEntity) =
        restaurantDao.insert(restaurantEntity)

    override suspend fun deleteRestaurantById(id: Int) =
        restaurantDao.deleteById(id)

    override suspend fun updateRestaurant(restaurantEntity: RestaurantEntity) =
        restaurantDao.update(restaurantEntity)
}
