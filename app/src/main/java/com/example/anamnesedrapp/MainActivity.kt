package com.example.anamnesedrapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.usuario.ui.HomeFragment
import com.example.anamnesedrapp.ui.util.TopbarApp
import com.example.anamnesedrapp.usuario.ui.LoginFragment
import com.example.anamnesedrapp.usuario.ui.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val sTelaInicial = mutableStateOf("home")

    @Inject
    lateinit var loginFragment: LoginFragment

    @Inject
    lateinit var homeFragment: HomeFragment

    @Inject
    lateinit var registerFragment: RegisterFragment

    lateinit var navHostController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnamneseDrAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.surface
//                ) {
                navegacao()
//                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun navegacao() {
        this.navHostController = rememberNavController()

        if (mainViewModel.bUsuarioAutenticado)
            sTelaInicial.value = "home"
        else
            sTelaInicial.value = "login"

        NavHost(
            navController = this.navHostController,
            startDestination = sTelaInicial.value,
            builder = {
                composable("home") { homeFragment.onCreate(navHostController) }
                composable("register") { registerFragment.onCreate(navHostController) }
                composable("login") { loginFragment.onCreate(navHostController) }
            }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
    @Composable
    fun DefaultPreview() {
            BaseTelaApp(
                scaffoldModifier = Modifier
                    .fillMaxWidth(),
                surfaceModifier = Modifier
                    .fillMaxWidth(),
                surfaceColor = MaterialTheme.colorScheme.surface,
                topbar = {
                    TopbarApp()
                }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .paint(
                            painterResource(id = R.mipmap.ic_logo_foreground),
                            alpha = 0.25f
                        )
                ) {
                    Text(text = "teste")
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "teste") },
                    )
                }
            }
    }
}