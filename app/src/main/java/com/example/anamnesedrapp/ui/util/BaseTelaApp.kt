package com.example.anamnesedrapp.ui.util

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTelaApp(
    scaffoldModifier: Modifier = Modifier,
    scaffoldContainerColor: Color = MaterialTheme.colorScheme.background,
    scaffoldContentColor: Color = contentColorFor(scaffoldContainerColor),
    surfaceModifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    topbar: @Composable () -> Unit = {},
    surfaceContent: @Composable () -> Unit = {}
) {
    AnamneseDrAppTheme {
        Scaffold(
            modifier = scaffoldModifier,
            topBar = { topbar() },
            containerColor = scaffoldContainerColor,
            contentColor = scaffoldContentColor,
            content = {
                Surface(
                    modifier = surfaceModifier,
                    color = surfaceColor,
                ) {
                    surfaceContent()
                }
            }
        )
    }
}

//@Composable
//fun BaseTela2(
//
//){
//    AnamneseDrAppTheme {
//        BackdropScaffold(appBar = { /*TODO*/ }, backLayerContent = { /*TODO*/ }) {
//
//        }
//    }
//}