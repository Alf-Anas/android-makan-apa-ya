package dev.geoit.makanapaya.views.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.LocalRestaurantRepository
import dev.geoit.makanapaya.data.Restaurant
import dev.geoit.makanapaya.data.convertRestaurantEntityListToRestaurantList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalNavigateEdit = compositionLocalOf<(Int) -> Unit> {
    error("No Navigation provided")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navigateAdd: () -> Unit, navigateEdit: (Int) -> Unit) {
    val restaurantRepository = LocalRestaurantRepository.current
    var restaurantList by remember { mutableStateOf<List<Restaurant>>(emptyList()) }

    LaunchedEffect(restaurantRepository) {
        withContext(Dispatchers.IO) {
            restaurantRepository.getAllRestaurantStream().collect {
                restaurantList = convertRestaurantEntityListToRestaurantList(it)
            }
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = navigateAdd,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Restaurant"
            )
        }
    }) { innerPadding ->
        CompositionLocalProvider(
            LocalNavigateEdit provides navigateEdit
        ) {
            RestaurantList(
                restaurantList = restaurantList,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }

}