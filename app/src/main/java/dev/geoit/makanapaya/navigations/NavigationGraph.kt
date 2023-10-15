package dev.geoit.makanapaya.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.geoit.makanapaya.destinations.Destinations
import dev.geoit.makanapaya.views.add_restaurant.AddRestaurantScreen
import dev.geoit.makanapaya.views.history.HistoryScreen
import dev.geoit.makanapaya.views.home.HomeScreen
import dev.geoit.makanapaya.views.list.ListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.History.route) {
            HistoryScreen()
        }
        composable(Destinations.List.route) {
            ListScreen(
                navigateAdd = { navController.navigate(Destinations.AddRestaurant.route) },
                navigateEdit = { itemId -> navController.navigate(Destinations.AddRestaurant.route + "/$itemId") }
            )
        }
        composable(Destinations.AddRestaurant.route) {
            AddRestaurantScreen(
                navigateBack = { navController.navigate(Destinations.List.route) },
                onNavigateUp = { navController.navigate(Destinations.List.route) })
        }
        composable("${Destinations.AddRestaurant.route}/{id}") { navBackStackEntry ->
            val restaurantId = navBackStackEntry.arguments?.getString("id")
            restaurantId?.let { id ->
                AddRestaurantScreen(
                    navigateBack = { navController.navigate(Destinations.List.route) },
                    onNavigateUp = { navController.navigate(Destinations.List.route) },
                    restaurantId = id.toInt()
                )
            }
        }
    }
}