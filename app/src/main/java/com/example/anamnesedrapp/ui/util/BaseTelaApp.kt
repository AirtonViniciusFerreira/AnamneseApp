package com.example.anamnesedrapp.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.APP_Primary_Blue_100
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme


@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun BaseTelaApp(
    scaffoldModifier: Modifier = Modifier,
    scaffoldContainerColor: Color = MaterialTheme.colorScheme.background,
    scaffoldContentColor: Color = contentColorFor(scaffoldContainerColor),
    surfaceModifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    topbar: @Composable () -> Unit = {},
    surfaceContent: @Composable () -> Unit = {},
) {
    AnamneseDrAppTheme() {
        Scaffold(
            modifier = scaffoldModifier,
            topBar = { topbar() },
            containerColor = scaffoldContainerColor,
            contentColor = scaffoldContentColor,
            content = { innerPadding ->
                Surface(
                    modifier = surfaceModifier.consumeWindowInsets(innerPadding),
                    color = surfaceColor,
                ) {
                    surfaceContent()
                }
            }
        )
    }
}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseTelaAPP() {
    BaseTelaApp(
        surfaceColor = MaterialTheme.colorScheme.primary
    ) {

    }
}