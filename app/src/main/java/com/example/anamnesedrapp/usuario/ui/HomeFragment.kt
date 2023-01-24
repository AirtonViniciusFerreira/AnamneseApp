package com.example.anamnesedrapp.usuario.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TopbarApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor(
//    val mainViewModel: MainViewModel
) : Fragment() {

    lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun onCreate(navHostController: NavHostController) {
        this.navHostController = navHostController
        BaseTelaApp(
            topbar = {
                TopbarApp(R.string.home_titulo)
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Bem Vindo!", color = MaterialTheme.colorScheme.secondary)
            }
        }
    }

    @Preview
    @Composable
    fun previewHome() {
        onCreate(rememberNavController())
    }
}