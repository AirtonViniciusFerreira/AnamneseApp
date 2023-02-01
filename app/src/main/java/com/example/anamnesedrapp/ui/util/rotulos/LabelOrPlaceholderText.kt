package com.example.anamnesedrapp.ui.util

import androidx.annotation.StringRes
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.anamnesedrapp.R

@Composable
fun LabelOrPlaceholderText(
    @StringRes idText: Int = R.string.string_vazia,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = stringResource(id = idText),
        modifier = modifier,
        color = color,
        style = style
    )
}