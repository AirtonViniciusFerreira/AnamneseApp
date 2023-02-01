package com.example.anamnesedrapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.anamnesedrapp.tutor.ui.TutorEdit
import com.example.anamnesedrapp.tutor.ui.TutorList
import com.example.anamnesedrapp.tutor.ui.vm.TutorViewModel
import com.example.anamnesedrapp.ui.ConstrucaoAPP
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TopbarApp
import com.example.anamnesedrapp.usuario.ui.*
import com.example.anamnesedrapp.usuario.ui.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@ExperimentalMaterialApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val sTelaInicial = mutableStateOf("home")

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
        val mainViewModel1 = hiltViewModel<MainViewModel>()
        val tutorViewModel = hiltViewModel<TutorViewModel>()

        if (mainViewModel.bUsuarioAutenticado)
            sTelaInicial.value = "home"
        else
            sTelaInicial.value = "login"

        NavHost(
            navController = this.navHostController,
            startDestination = sTelaInicial.value,
            builder = {
                composable("home") {
                    HomeOnCreate(navHostController =  navHostController, mainViewModel1)
                }
//                navigation(startDestination = "usuario", route = "usuario") {
                    composable("register") { registerFragment.onCreate(navHostController) }
                    composable("login") {
                        val loginViewModel = hiltViewModel<LoginViewModel>()
                        LoginOnCreate(navHostController =  navHostController, loginVM =  loginViewModel, mainVM = mainViewModel1)
                    }
//                }
                navigation(startDestination = "tutor/list", route = "tutor") {
                    composable("tutor/list") {
                        TutorList(navHostController = navHostController,
                            tutorViewModel = tutorViewModel
                        )
                    }
                    composable(
                        route ="tutor/edit?tutorId={tutorId}",
                        arguments = listOf(navArgument(name = "tutorId") {
                            nullable = true
                            type = NavType.StringType
                            defaultValue = null
                        })
                    ) {backStackEntry ->
                        TutorEdit(
                            navHostController = navHostController,
                            tutorViewModel = tutorViewModel,
                            tutorId = backStackEntry.arguments?.getString("tutorId")
                        )
                    }
                }


                composable("em-construcao") {
                    ConstrucaoAPP(navHostController = navHostController)
                }
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

            }
    }
}

var LocalListMenus = staticCompositionLocalOf {
    listOf(
        Menu(
            titulo = R.string.consulta_titulo,
            icone = R.drawable.baseline_do_disturb_24
        ),
//            Menu(
//                titulo = R.string.historico_medico_titulo,
//            ),
        Menu(
            titulo =  R.string.pet_titulo,
            icone = R.drawable.baseline_pets_24,
        ),
        Menu(
            titulo = R.string.tutor_titulo,
            urlNavegacao = "tutor/list",
            icone = R.drawable.baseline_man_24
        ),
        Menu(
            titulo = R.string.medico_titulo,
            icone = R.drawable.baseline_medical_information_24
        )
    )
}