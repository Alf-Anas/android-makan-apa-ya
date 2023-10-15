package dev.geoit.makanapaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.geoit.makanapaya.destinations.Destinations
import dev.geoit.makanapaya.navigations.MainBottomNavigation
import dev.geoit.makanapaya.navigations.NavigationGraph
import dev.geoit.makanapaya.room.AppDataContainer
import dev.geoit.makanapaya.room.HistoryRepository
import dev.geoit.makanapaya.room.RestaurantRepository
import dev.geoit.makanapaya.ui.theme.MakanApaYaTheme
import dev.geoit.makanapaya.views.home.DiceWithButtonViewModel


val LocalDiceWithButtonViewModel =
    compositionLocalOf { DiceWithButtonViewModel() }

val LocalHistoryRepository = compositionLocalOf<HistoryRepository> {
    error("No HistoryRepository provided")
}

val LocalRestaurantRepository = compositionLocalOf<RestaurantRepository> {
    error("No RestaurantRepository provided")
}


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = AppDataContainer(applicationContext)
        val historyRepository = container.historyRepository
        val restaurantRepository = container.restaurantRepository
        setContent {
            MakanApaYaTheme {
                val navController: NavHostController = rememberNavController()
                val showMainBottomNavigation = remember { mutableStateOf(true) }

                navController.addOnDestinationChangedListener { _, destination, _ ->
                    showMainBottomNavigation.value =
                        destination.route == Destinations.HomeScreen.route || destination.route == Destinations.History.route || destination.route == Destinations.List.route
                }

                val diceWithButtonViewModel = DiceWithButtonViewModel()
                diceWithButtonViewModel.historyRepository = historyRepository

                Scaffold(
                    bottomBar = {
                        MainBottomNavigation(
                            navController = navController,
                            state = showMainBottomNavigation,
                            modifier = Modifier
                        )
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        CompositionLocalProvider(
                            LocalDiceWithButtonViewModel provides diceWithButtonViewModel,
                            LocalHistoryRepository provides historyRepository,
                            LocalRestaurantRepository provides restaurantRepository
                        ) {
                            NavigationGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
