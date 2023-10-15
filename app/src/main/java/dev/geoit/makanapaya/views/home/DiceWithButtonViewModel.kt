package dev.geoit.makanapaya.views.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.geoit.makanapaya.constant.LoadingState
import dev.geoit.makanapaya.room.Converters
import dev.geoit.makanapaya.room.HistoryEntity
import dev.geoit.makanapaya.room.HistoryRepository
import dev.geoit.makanapaya.utils.getCurrentTime
import dev.geoit.makanapaya.utils.randomDiceFace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DiceWithButtonViewModel : ViewModel() {
    var currentDiceFace by mutableIntStateOf(1)
    var result by mutableIntStateOf(1)
    var loadingState by mutableStateOf(LoadingState.IDLE)
    private var diceRollingCoroutine: Job? = null


    var foodList by mutableStateOf(emptyList<String>())
    var listFoodRecommendation by mutableStateOf(emptyList<String>())
    var historyRepository: HistoryRepository? = null


    fun rollDice() {
        loadingState = LoadingState.LOADING

        diceRollingCoroutine?.cancel()
        diceRollingCoroutine = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                currentDiceFace = randomDiceFace()
                delay(75)
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            diceRollingCoroutine?.cancel()
            result = currentDiceFace
            updateListFoodRecommendation()
        }
    }

    private suspend fun updateListFoodRecommendation() {
        loadingState = LoadingState.SUCCESS
        listFoodRecommendation = if (foodList.size < result) {
            foodList.shuffled()
        } else {
            foodList.shuffled().subList(0, result)
        }

        if (listFoodRecommendation.isNotEmpty()) {
            historyRepository?.insertHistory(
                HistoryEntity(
                    generatedAt = getCurrentTime(),
                    recommendations = Converters().saveList(listFoodRecommendation)
                )
            )
        }

    }

}

