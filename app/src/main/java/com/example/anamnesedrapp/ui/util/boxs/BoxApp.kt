package com.example.anamnesedrapp.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.anamnesedrapp.ui.util.listas.ListaApp
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun BoxAppCardDeslize(
    squareSize: Dp = LocalConfiguration.current.screenWidthDp.dp/2,
    boxModifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    contentBox: @Composable () -> Unit = {},
    contentCard: @Composable () -> Unit = {}
) {
    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors =
        mapOf(0f to 0, sizePx to 2) // Maps anchor points (in px) to states
    Box(
        modifier = boxModifier
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
//            .background(Color.LightGray)
    ) {
            Card(
                modifier = cardModifier
                    .offset {
                        IntOffset(
                            swipeableState.offset.value.roundToInt(),
                            0
                        )
                    }
                    .fillMaxWidth()
                ,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground)
            ) {

               contentCard()
            }
        contentBox()
    }
}


@ExperimentalMaterial3Api
@ExperimentalMaterialApi
@Composable
fun BoxAppListDeslize(
    squareSize: Dp = LocalConfiguration.current.screenWidthDp.dp/2,
    swipeableInicial: Int = 0,
    swipeableState: androidx.compose.material.SwipeableState<Int> =rememberSwipeableState(swipeableInicial),
    boxModifier: Modifier = Modifier,
    headlineText: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    overlineText: (@Composable () -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    colors: ListItemColors = ListItemDefaults.colors(),
    contentBox: @Composable () -> Unit = {},
) {
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors =
        mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states
    Box(
        modifier = boxModifier
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
//            .background(Color.LightGray)
    ) {
        ListaApp(
            headlineText = headlineText,
            modifier = modifier,
            overlineText = overlineText,
            leadingContent = leadingContent,
            trailingContent =  {
                Box(
                    modifier = Modifier .offset {
                        IntOffset(
                            swipeableState.offset.value.roundToInt(),
                            0
                        )
                    }
                ) {
                    trailingContent?.let { it() }

                }
            },
            colors = colors
        )
        contentBox()
    }
}
