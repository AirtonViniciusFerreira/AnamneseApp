package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@Composable
fun BotaoFlutuanteAdd(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.clip(RoundedCornerShape(50))
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
    ) {
        IconeAdicionar()
    }
}

@Preview(name = "Botoes Flutuantes")
@Preview(name = "Botoes Flutuantes", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewBotoesFlutuantes() {
    AnamneseDrAppTheme() {
        Column() {
            BotaoFlutuanteAdd(onClick = { /*TODO*/ })
        }
    }
}