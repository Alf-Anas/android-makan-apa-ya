package dev.geoit.makanapaya.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object History : Destinations(
        route = "history",
        title = "History",
        icon = Icons.Outlined.Refresh
    )

    object List : Destinations(
        route = "list",
        title = "Menu",
        icon = Icons.Outlined.List
    )

    object AddRestaurant : Destinations(
        route = "add_restaurant",
        title = "Add Restaurant",
        icon = Icons.Outlined.Add
    )

}