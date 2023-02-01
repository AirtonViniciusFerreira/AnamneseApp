package com.example.anamnesedrapp.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.FontFamilyTitulo

@Composable
fun TituloApp(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    TituloApp(
        modifier = modifier,
        color = color,
        style = MaterialTheme.typography.displayLarge
    )
}

@Composable
fun TituloApp(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = MaterialTheme.typography.displayLarge
) {
    TituloApp(
        modifier = modifier,
        style = style,
        color = color,
        fontFamily = FontFamilyTitulo
    )
}

@Composable
fun TituloApp(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = MaterialTheme.typography.displayLarge,
    fontFamily: FontFamily = FontFamilyTitulo
) {
    Text(
        text = stringResource(id = R.string.app_name),
        modifier = modifier,
        style = style,
        color = color,
        fontFamily = fontFamily
    )
}

@Composable
@Preview
private fun PreviewTituloAPP() {
    TituloApp()
}
