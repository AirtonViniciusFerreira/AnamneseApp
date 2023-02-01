package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.theme.Black
import com.example.anamnesedrapp.ui.theme.White

@Composable
fun IconeCancel() {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
        contentDescription = stringResource(id = R.string.icon_cancel_description)
    )
}

@Composable
fun IconeMenu(
    tint: Color = White
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_menu_24),
        contentDescription = stringResource(id = R.string.icon_menu_description),
        tint = tint
    )
}

@Composable
fun IconeAdicionar(
    modifier: Modifier = Modifier
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max),
    tint: Color = Black,
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_add_24),
        contentDescription = stringResource(id = R.string.icon_add_description),
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconeEditar(
    modifier: Modifier = Modifier
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max),
    tint: Color = Black
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_edit_24),
        contentDescription = stringResource(id = R.string.icon_edit_description),
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconeDeletar(
    modifier: Modifier = Modifier
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max),
    tint: Color = Black
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_delete_24),
        contentDescription = stringResource(id = R.string.icon_delete_description),
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun IconeFlechaBaixo(
    modifier: Modifier = Modifier
        .width(IntrinsicSize.Max)
        .height(IntrinsicSize.Max),
    tint: Color = Black
) {
    Icon(
        painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
        contentDescription = stringResource(id = R.string.icon_arrow__drop_down_description),
        modifier = modifier,
//        tint =  tint
    )
}

@Preview(name = "Icones")
@Preview(name = "Dark Icones", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewIcones() {
    AnamneseDrAppTheme {
        Column {
            IconeCancel()
            IconeMenu()
            IconeAdicionar()
            IconeEditar()
            IconeDeletar()
            IconeFlechaBaixo()
        }
    }
}