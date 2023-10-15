package dev.geoit.makanapaya.room

import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    /**
     * Retrieve all the restaurants from the the given data source.
     */
    fun getAllRestaurantStream(): Flow<List<RestaurantEntity>>

    /**
     * Retrieve all the restaurants active now.
     */
    fun getAllActiveRestaurantStream(): Flow<List<RestaurantEntity>>

    /**
     * Retrieve restaurant by id
     */
    fun getRestaurantByIdStream(id: Int): Flow<RestaurantEntity?>

    /**
     * Insert restaurant in the data source
     */
    suspend fun insertRestaurant(restaurantEntity: RestaurantEntity)

    /**
     * Delete restaurant from the data source
     */
    suspend fun deleteRestaurantById(id: Int)

    /**
     * Update restaurant in the data source
     */
    suspend fun updateRestaurant(restaurantEntity: RestaurantEntity)

}
