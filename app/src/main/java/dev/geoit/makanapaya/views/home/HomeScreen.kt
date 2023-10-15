package dev.geoit.makanapaya.views.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.geoit.makanapaya.LocalDiceWithButtonViewModel
import dev.geoit.makanapaya.LocalRestaurantRepository
import dev.geoit.makanapaya.data.convertRestaurantEntityListToRestaurantList
import dev.geoit.makanapaya.utils.getFoodFromRestaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen() {
    val localDiceWithButtonViewModel = LocalDiceWithButtonViewModel.current
    val restaurantRepository = LocalRestaurantRepository.current

    LaunchedEffect(restaurantRepository) {
        withContext(Dispatchers.IO) {
            restaurantRepository.getAllActiveRestaurantStream().collect {
                val restaurantList = convertRestaurantEntityListToRestaurantList(it)
                localDiceWithButtonViewModel.foodList = getFoodFromRestaurant(restaurantList)
            }
        }
    }

    DiceWithButton(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    )
}