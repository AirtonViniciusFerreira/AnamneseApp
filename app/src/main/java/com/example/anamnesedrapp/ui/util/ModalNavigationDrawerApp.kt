package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@Composable
fun ModalNavigationDrawerApp(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Open),
    content: @Composable () -> Unit = {}
){
    ModalNavigationDrawer(
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.5F)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        TituloApp(
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                    Text(text = "Teste 2")
                }
            }
        },
        modifier = Modifier,
        drawerState = drawerState

    ){
        content()
    }
}

@Preview(name = "Modal Navegação Graveta")
@Preview(name = "Dark Modal Navegação Graveta", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewModelNavigationDrawerAPP() {
    AnamneseDrAppTheme {
        ModalNavigationDrawerApp()
    }
}

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Preview(name = "Modal Navegação Graveta com a Base")
@Preview(name = "Dark Modal Navegação Graveta com a Base", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewModelNavigationDrawerAPPWithBase() {
    AnamneseDrAppTheme {
        ModalNavigationDrawerApp(){
            BaseTelaApp(
                topbar =  {
                    TopAppBarCenter(
                        iconeMenuOnClick =  {

                        }
                    )
                }
            ) {

            }
        }

    }
}