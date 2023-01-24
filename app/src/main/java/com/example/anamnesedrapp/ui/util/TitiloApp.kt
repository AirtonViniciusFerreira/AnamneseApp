package com.example.anamnesedrapp.ui.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.anamnesedrapp.R

@Composable
fun TituloApp(
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 50.sp
) {
    Text(
        text = stringResource(id = R.string.app_name),
        modifier = modifier,
//        fontSize = fontSize,
//        fontFamily = FontFamily(Font(R.font.frscript)),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
@Preview
private fun PreviewTituloAPP() {
    TituloApp()
}
