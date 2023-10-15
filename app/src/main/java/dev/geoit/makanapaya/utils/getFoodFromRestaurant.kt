package dev.geoit.makanapaya.utils

import dev.geoit.makanapaya.data.Restaurant

fun getFoodFromRestaurant(restaurantList: List<Restaurant>): List<String> {
    val normalizedRestaurantList = mutableListOf<String>()
    for (restaurant in restaurantList) {
        for (menu in restaurant.menu) {
            normalizedRestaurantList.add("${restaurant.name} - $menu")
        }
    }
    return normalizedRestaurantList
}