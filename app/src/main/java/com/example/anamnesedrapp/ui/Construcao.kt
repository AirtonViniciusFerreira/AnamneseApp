package com.example.anamnesedrapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloApp
import com.example.anamnesedrapp.ui.util.TituloFragment

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun ConstrucaoAPP(
    navHostController: NavHostController
) {
    Dialog(
        onDismissRequest = {
            navHostController.popBackStack()
        }
    ) {
        Surface() {
            Column() {
                TituloApp()
                TituloFragment(
                    titulo = "Descupe essa tela ainda está em Construcao!"
                )
            }
        }
    }
}


@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview(name = "Construcao Tela")
@Preview(name = "Dark Construcao Tela", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewConstrucaoAPP() {
    AnamneseDrAppTheme {
        ConstrucaoAPP(rememberNavController())
    }
}

