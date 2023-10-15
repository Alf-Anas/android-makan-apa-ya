package dev.geoit.makanapaya.data

import dev.geoit.makanapaya.room.Converters
import dev.geoit.makanapaya.room.RestaurantEntity
import dev.geoit.makanapaya.utils.getCurrentTime

data class Restaurant(
    val id: Int?,
    val name: String,
    val openHour: Int,
    val closeHour: Int,
    val menu: List<String>,
    val createdAt: String?,
)

fun convertRestaurantEntityToRestaurant(restaurantEntity: RestaurantEntity): Restaurant {
    return Restaurant(
        id = restaurantEntity.id,
        name = restaurantEntity.name,
        openHour = restaurantEntity.openHour,
        closeHour = restaurantEntity.closeHour,
        menu = Converters().restoreList(restaurantEntity.menu),
        createdAt = restaurantEntity.createdAt,
    )
}


fun convertRestaurantEntityListToRestaurantList(restaurantEntityList: List<RestaurantEntity>): List<Restaurant> {
    val restaurantEntries = mutableListOf<Restaurant>()
    for (restaurantEntity in restaurantEntityList) {
        restaurantEntries.add(
            convertRestaurantEntityToRestaurant(restaurantEntity)
        )
    }
    return restaurantEntries
}

fun convertRestaurantToRestaurantEntity(restaurant: Restaurant): RestaurantEntity {
    val restaurantEntity = RestaurantEntity(
        name = restaurant.name,
        openHour = restaurant.openHour,
        closeHour = restaurant.closeHour,
        menu = Converters().saveList(restaurant.menu),
        createdAt = restaurant.createdAt ?: getCurrentTime(),
    )
    if (restaurant.id != null) {
        restaurantEntity.id = restaurant.id
    }
    return restaurantEntity
}

//fun convertRestaurantListToRestaurantEntityList(restaurantList: List<Restaurant>): List<RestaurantEntity> {
//    val restaurantEntities = mutableListOf<RestaurantEntity>()
//    for (restaurant in restaurantList) {
//        restaurantEntities.add(
//            convertRestaurantToRestaurantEntity(restaurant)
//        )
//    }
//    return restaurantEntities
//}

