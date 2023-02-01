package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.botoes.BotaoPreenchidoDetelar
import com.example.anamnesedrapp.ui.util.botoes.BotaoPreenchidoEditar

@Composable
fun BottomBarApp(
    rowModifier: Modifier = Modifier.fillMaxWidth().clipScrollableContainer(Orientation.Horizontal),
    surfaceModifier: Modifier = Modifier.fillMaxWidth(),
    surfaceColor: Color = MaterialTheme.colorScheme.secondary,
    content: @Composable () -> Unit = {}
) {
//    Surface(
//        modifier = surfaceModifier,
//        color = surfaceColor,
//    ) {
        ScrollableTabRow(
            selectedTabIndex = 0,
            modifier = rowModifier,
        ) {
            content()
        }
//    }
}

@Preview(name = "Bottom Bar")
@Preview(name = "Dark Bottom Bar", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewBottomBarApp() {
    AnamneseDrAppTheme {
        BottomBarApp() {
            BotaoPreenchidoEditar(onClick = { /*TODO*/ })
            BotaoPreenchidoEditar(onClick = { /*TODO*/ })
            BotaoPreenchidoEditar(onClick = { /*TODO*/ })
            BotaoPreenchidoDetelar(onClick = { /*TODO*/ })
            BotaoPreenchidoDetelar(onClick = { /*TODO*/ })
            BotaoPreenchidoDetelar(onClick = { /*TODO*/ })
        }
    }
}