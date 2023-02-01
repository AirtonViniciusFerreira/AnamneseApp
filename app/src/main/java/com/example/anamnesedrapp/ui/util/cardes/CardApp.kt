package com.example.anamnesedrapp.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun CardeListaApp(
    modifier: Modifier = Modifier
//        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 5.dp),
    colors: CardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary,),
    border: BorderStroke? = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
    content: @Composable () -> Unit = {}
) {
    Card(
        modifier = modifier,
        colors = colors,
        border = border
    ) {
        content()
    }
}
