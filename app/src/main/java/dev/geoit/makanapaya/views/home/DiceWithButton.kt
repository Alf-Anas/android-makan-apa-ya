package dev.geoit.makanapaya.views.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.geoit.makanapaya.LocalDiceWithButtonViewModel
import dev.geoit.makanapaya.R
import dev.geoit.makanapaya.constant.LoadingState

val buttonRollStringIds = arrayOf(
    R.string.btn_roll_0,
    R.string.btn_roll_1,
    R.string.btn_roll_2,
    R.string.btn_roll_3,
    R.string.btn_roll_4,
    R.string.btn_roll_5,
    R.string.btn_roll_6,
    R.string.btn_roll_7,
    R.string.btn_roll_8,
    R.string.btn_roll_9,
    R.string.btn_roll_10
)

@Composable
fun DiceWithButton(modifier: Modifier = Modifier) {
    val viewModel = LocalDiceWithButtonViewModel.current
    val imageResource = when (viewModel.currentDiceFace) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = viewModel.currentDiceFace.toString()
        )

        AnimatedVisibility(
            visible = viewModel.loadingState == LoadingState.SUCCESS,
            enter = fadeIn(tween(durationMillis = 300)),
            exit = fadeOut(tween(durationMillis = 200))

        ) {
            Column(modifier = Modifier.padding(bottom = 24.dp, start = 24.dp, end = 24.dp)) {
                viewModel.listFoodRecommendation.forEachIndexed { idx, it ->
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(0.dp, 6.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = when (idx) {
                            0 -> 20.sp
                            1 -> 18.sp
                            2 -> 16.sp
                            else -> 14.sp
                        },
                        textAlign = TextAlign.Center
                    )
                }
                if (viewModel.listFoodRecommendation.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.info_no_restaurant_open),
                        modifier = Modifier
                            .padding(0.dp, 6.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (viewModel.loadingState != LoadingState.LOADING) {
            Button(
                onClick = {
                    viewModel.rollDice()
                },
            ) {
                Icon(
                    when (viewModel.loadingState) {
                        LoadingState.IDLE -> Icons.Filled.Star
                        else -> Icons.Filled.Refresh
                    }, contentDescription = "Roll icon"
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = stringResource(
                        when (viewModel.loadingState) {
                            LoadingState.IDLE -> buttonRollStringIds.random()
                            else -> R.string.btn_re_roll
                        }
                    ), fontSize = 24.sp
                )
            }
        }
    }
}