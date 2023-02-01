package com.example.anamnesedrapp.ui.util

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.White

@Composable
fun TituloFragment(
    @StringRes idTitulo:  Int = R.string.string_vazia,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = stringResource(id = idTitulo),
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = color
    )
}

@Composable
fun TituloFragment(
    titulo:  String = "",
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text = titulo,
        modifier = modifier,
        style = MaterialTheme.typography.displayMedium,
        color = color
    )
}