package dev.geoit.makanapaya.views.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.data.Restaurant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCard(restaurant: Restaurant, modifier: Modifier = Modifier) {
    val navigateEdit = LocalNavigateEdit.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        onClick = { restaurant.id?.let { navigateEdit(it) } }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
            )
            for (menu in restaurant.menu) {
                Text(
                    text = "- $menu",
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Open From ${restaurant.openHour} to ${restaurant.closeHour}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 4.dp)
            )
        }
    }
}
