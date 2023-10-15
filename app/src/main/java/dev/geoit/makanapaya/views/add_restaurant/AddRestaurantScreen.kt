package dev.geoit.makanapaya.views.add_restaurant

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.geoit.makanapaya.LocalRestaurantRepository
import dev.geoit.makanapaya.R
import dev.geoit.makanapaya.data.Restaurant
import dev.geoit.makanapaya.navigations.TopBarApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRestaurantScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    restaurantId: Int? = null
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel = remember { AddRestaurantViewModel() }
    val localRestaurantRepository = LocalRestaurantRepository.current

    LaunchedEffect(restaurantId, localRestaurantRepository) {
        withContext(Dispatchers.IO) {
            viewModel.restaurantRepository = localRestaurantRepository
            if (restaurantId != null) {
                viewModel.getRestaurantData(restaurantId)
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarApp(
                title = stringResource(R.string.screen_add_restaurant),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
            )
        },
        modifier = modifier
    ) { innerPadding ->
        val context = LocalContext.current
        val messageAdded = stringResource(id = R.string.toast_restaurant_added)
        val messageUpdated = stringResource(id = R.string.toast_restaurant_updated)
        val messageDeleted = stringResource(id = R.string.toast_restaurant_deleted)
        RestaurantEntryBody(
            restaurantUiState = viewModel.restaurantUiState,
            onValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                    Toast.makeText(
                        context,
                        if (viewModel.restaurantUiState.inputRestaurant.id == null) messageAdded else messageUpdated,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                    Toast.makeText(context, messageDeleted, Toast.LENGTH_SHORT).show()
                }
            },
        )
    }

}


@Composable
fun RestaurantEntryBody(
    restaurantUiState: RestaurantUiState,
    onValueChange: (Restaurant) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(12.dp)
    ) {
        RestaurantInputForm(
            onValueChange = onValueChange,
            restaurant = restaurantUiState.inputRestaurant,
            modifier = Modifier.fillMaxWidth(),
            isInputValid = restaurantUiState.isEntryValid
        )
        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = restaurantUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.btn_save))
        }

        if (restaurantUiState.inputRestaurant.id != null) {
            Divider(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = onDeleteClick,
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.btn_delete))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantInputForm(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    onValueChange: (Restaurant) -> Unit = {},
    enabled: Boolean = true,
    isInputValid: Boolean = true,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        OutlinedTextField(
            value = restaurant.name,
            onValueChange = { onValueChange(restaurant.copy(name = it)) },
            label = { Text(stringResource(R.string.input_name)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = restaurant.openHour.toString(),
            onValueChange = { onValueChange(restaurant.copy(openHour = if (it.isNotEmpty()) it.toInt() else 0)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.input_open)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = restaurant.closeHour.toString(),
            onValueChange = { onValueChange(restaurant.copy(closeHour = if (it.isNotEmpty()) it.toInt() else 0)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.input_close)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Text(
            text = stringResource(R.string.input_menu),
        )
        restaurant.menu.forEachIndexed { idx, it ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            ) {
                OutlinedTextField(
                    value = it,
                    onValueChange = {
                        val newMenu = restaurant.menu.toMutableList()
                        newMenu[idx] = it
                        onValueChange(restaurant.copy(menu = newMenu))
                    },
                    enabled = enabled,
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                )
                OutlinedButton(
                    onClick = {
                        val newMenu = restaurant.menu.toMutableList()
                        newMenu.removeAt(idx)
                        onValueChange(restaurant.copy(menu = newMenu))
                    },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .padding(start = 8.dp)
                ) {
                    Text("-")
                }
            }

        }
        OutlinedButton(
            onClick = {
                val newMenu = restaurant.menu.toMutableList()
                newMenu.add("")
                onValueChange(restaurant.copy(menu = newMenu))
            },
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(id = R.string.btn_add_menu))
        }
        if (!isInputValid) {
            Text(
                text = stringResource(R.string.input_invalid),
                color = Color.Red
            )
        }
    }
}
