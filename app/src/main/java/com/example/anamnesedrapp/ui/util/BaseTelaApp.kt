package com.example.anamnesedrapp.ui.util

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.R


@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun BaseTelaApp(
    scaffoldModifier: Modifier = Modifier,
    scaffoldContainerColor: Color = MaterialTheme.colorScheme.background,
    scaffoldContentColor: Color = contentColorFor(scaffoldContainerColor),
    floatingActionButton: @Composable () -> Unit = {},
    surfaceModifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    topbar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    surfaceContent: @Composable () -> Unit = {},
) {
    AnamneseDrAppTheme() {
        Scaffold(
            modifier = scaffoldModifier,
            topBar = { topbar() },
            bottomBar = { bottomBar() },
            floatingActionButton  = floatingActionButton,
            containerColor = scaffoldContainerColor,
            contentColor = scaffoldContentColor,
            content = { innerPadding ->

                Surface(
                    modifier = surfaceModifier
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding()
                        )
                        .consumeWindowInsets(innerPadding),
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
        topbar = { TopAppBarCenter(idTitle = R.string.app_name) },
        surfaceColor = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TituloApp()
        }
    }
}