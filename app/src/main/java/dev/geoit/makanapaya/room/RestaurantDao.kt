package dev.geoit.makanapaya.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurantEntity: RestaurantEntity)

    @Update
    suspend fun update(restaurantEntity: RestaurantEntity)

    @Query("DELETE FROM restaurant WHERE id = :restaurantId")
    suspend fun deleteById(restaurantId: Int)

    @Query("SELECT * from restaurant ORDER BY createdAt ASC")
    fun getAllRestaurant(): Flow<List<RestaurantEntity>>

    @Query("SELECT * from restaurant WHERE :localTime BETWEEN openHour AND closeHour ORDER BY createdAt ASC")
    fun getAllActiveRestaurant(localTime: Int): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurant WHERE id = :restaurantId")
    fun getRestaurantById(restaurantId: Int): Flow<RestaurantEntity?>
}