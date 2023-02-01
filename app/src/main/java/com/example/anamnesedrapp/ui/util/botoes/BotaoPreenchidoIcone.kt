package com.example.anamnesedrapp.ui.util.botoes

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.IconeDeletar
import com.example.anamnesedrapp.ui.util.IconeEditar


@Composable
fun BotaoPreenchidoEditar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    tintIcon: Color = MaterialTheme.colorScheme.onPrimary
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            IconeEditar(tint = tintIcon)
            Text("Editar")
        }
    }
}

@Composable
fun BotaoPreenchidoDetelar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(0),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    tintIcon: Color = MaterialTheme.colorScheme.onPrimary
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = colors
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconeDeletar(tint =  tintIcon)
            Text(text = "Deletar")
        }
    }
}

@Preview(name = "Botao Preenchido Icone")
@Preview(name = "Dark Botao Preenchido Icone", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewBotaoPreenchidoIcone() {
    AnamneseDrAppTheme {
        Column() {
            BotaoPreenchidoEditar(onClick = {})
            BotaoPreenchidoDetelar(onClick = {})
        }
    }
}