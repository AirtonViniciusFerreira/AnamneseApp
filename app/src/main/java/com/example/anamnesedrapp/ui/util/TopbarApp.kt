package com.example.anamnesedrapp.ui.util

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme


@Composable
fun TopbarApp(
    idTitle: Int = R.string.app_name,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
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
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}

@Composable
fun TopAppBarCenter(
    idTitle: Int = R.string.app_name,
    modifier: Modifier = Modifier,
    actions: @Composable (RowScope.() -> Unit) = {},
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.secondary
    ),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textModifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.onPrimary
) {
    AnamneseDrAppTheme {
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
                Icon(
                    painter = painterResource(id = R.drawable.baseline_menu_24),
                    contentDescription = "teste",
                    tint = iconTint
                )
            },
            actions = { actions() },
            colors = colors
        )
    }
}

@Preview
@Composable
fun PreviewTopbarAPP() {
    TopbarApp()
}

@Preview
@Composable
fun PreviewTopAppBar3() {
    TopAppBarCenter()
}