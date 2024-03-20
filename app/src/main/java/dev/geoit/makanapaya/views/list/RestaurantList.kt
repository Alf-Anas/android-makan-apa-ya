package dev.geoit.makanapaya.views.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.Restaurant

@Composable
fun RestaurantList(restaurantList: List<Restaurant>, modifier: Modifier = Modifier) {
    if (restaurantList.isEmpty()) {
        Card(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ){
            Text(
                text = "No restaurant found!",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp).fillMaxWidth()
            )
        }
    }
    LazyColumn(modifier = modifier) {
        items(restaurantList) { restaurant ->
            RestaurantCard(
                restaurant = restaurant,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}