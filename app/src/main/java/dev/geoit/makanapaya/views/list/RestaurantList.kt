package dev.geoit.makanapaya.views.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.Restaurant

@Composable
fun RestaurantList(restaurantList: List<Restaurant>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(restaurantList) { restaurant ->
            RestaurantCard(
                restaurant = restaurant,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}