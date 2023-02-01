package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@ExperimentalMaterial3Api
@Composable
fun TopAppBarCenter(
    idTitle: Int = R.string.app_name,
    modifier: Modifier = Modifier,
    actions: @Composable (RowScope.() -> Unit) = {},
    iconeMenuOnClick: () -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary
    ),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textModifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.onPrimary
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = idTitle),
                color = textColor,
                modifier = textModifier
            )
        },
        modifier = modifier,
        navigationIcon = {
            BotaoIconeMenu(
                onClick = iconeMenuOnClick
            )
        },
        actions = { actions() },
        colors = colors
    )
}

@ExperimentalMaterial3Api
@Composable
fun TopbarApp(
    idTitle: Int = R.string.app_name,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary
    ),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textModifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(idTitle),
                color = textColor,
                modifier = textModifier
            )
        },
        colors = colors,
        modifier = modifier
    )
}

//@ExperimentalMaterial3Api
//@Preview(name = "Top bar App")
//@Preview(name = "Dark Top bar App", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun PreviewTopbarAPP() {
//    AnamneseDrAppTheme() {
//        TopbarApp()
//    }
//}

@ExperimentalMaterial3Api
@Preview(name = "Top Bar Center")
@Preview(name = "Dark Top Bar Center", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewTopAppBar3() {
    AnamneseDrAppTheme() {
        TopAppBarCenter()
    }
}