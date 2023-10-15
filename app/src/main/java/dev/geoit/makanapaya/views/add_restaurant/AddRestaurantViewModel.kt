package dev.geoit.makanapaya.views.add_restaurant

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.geoit.makanapaya.data.Restaurant
import dev.geoit.makanapaya.data.convertRestaurantEntityToRestaurant
import dev.geoit.makanapaya.data.convertRestaurantToRestaurantEntity
import dev.geoit.makanapaya.room.RestaurantRepository
import dev.geoit.makanapaya.utils.validateInteger
import dev.geoit.makanapaya.utils.validateListString

class AddRestaurantViewModel : ViewModel() {

    var restaurantRepository: RestaurantRepository? = null

    var restaurantUiState by mutableStateOf(RestaurantUiState())
        private set

    fun updateUiState(restaurant: Restaurant) {
        restaurantUiState =
            RestaurantUiState(
                inputRestaurant = restaurant,
                isEntryValid = validateInput(restaurant)
            )
    }

    suspend fun getRestaurantData(id: Int) {
        restaurantRepository?.getRestaurantByIdStream(id)?.collect {
            if (it != null) {
                val restaurantData = convertRestaurantEntityToRestaurant(it)
                updateUiState(restaurantData)
            }
        }
    }


    suspend fun saveItem() {
        if (validateInput()) {
            if (restaurantUiState.inputRestaurant.id == null) {
                restaurantRepository?.insertRestaurant(
                    convertRestaurantToRestaurantEntity(
                        restaurantUiState.inputRestaurant
                    )
                )
            } else {
                restaurantRepository?.updateRestaurant(
                    convertRestaurantToRestaurantEntity(
                        restaurantUiState.inputRestaurant
                    )
                )
            }
        }
    }

    suspend fun deleteItem() {
        if (restaurantUiState.inputRestaurant.id != null) {
            restaurantRepository?.deleteRestaurantById(
                restaurantUiState.inputRestaurant.id!!
            )
        }
    }

    private fun validateInput(uiState: Restaurant = restaurantUiState.inputRestaurant): Boolean {
        return with(uiState) {
            name.isNotBlank() && validateInteger(openHour.toString()) && validateInteger(closeHour.toString())
                    && validateListString(menu) && openHour >= 0 && closeHour <= 24 && closeHour > openHour
        }
    }

}

data class RestaurantUiState(
    val inputRestaurant: Restaurant = Restaurant(
        id = null,
        name = "",
        openHour = 10,
        closeHour = 21,
        menu = listOf(""),
        createdAt = null
    ),
    val isEntryValid: Boolean = false
)