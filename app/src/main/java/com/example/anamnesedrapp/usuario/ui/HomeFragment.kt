package com.example.anamnesedrapp.usuario.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.LocalListMenus
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.BotaoFlutuanteAdd
import com.example.anamnesedrapp.ui.util.TituloFragment
import com.example.anamnesedrapp.ui.util.TopAppBarCenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun HomeOnCreate(
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    var lstMenu: List<Menu> = LocalListMenus.current

    BaseTelaApp(
        topbar = {
            TopAppBarCenter(R.string.home_titulo)
        },
        floatingActionButton = {
            BotaoFlutuanteAdd(
                onClick = {

                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mainViewModel.UsuarioLogado.value.isEmpty())
                TituloFragment(titulo = "Bem vindo!")
            else
                TituloFragment(titulo = "Bem vindo! ${mainViewModel.UsuarioLogado.value.first().nomeUsuario}")
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 120.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(lstMenu) { menu ->
                        IconButton(
                            onClick = {  navHostController.navigate(menu.urlNavegacao) },
                            modifier = Modifier
                                .fillMaxWidth(33.33f),
//                            containerColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Column(
                                modifier = Modifier.fillMaxHeight(50f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter =  painterResource(id = menu.icone),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxHeight(80f)
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = stringResource(id = menu.titulo),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                        FloatingActionButton(
                            onClick = {  navHostController.navigate(menu.urlNavegacao) },
                            modifier = Modifier
                                .fillMaxWidth(33.33f),
                            containerColor = MaterialTheme.colorScheme.primary,
                        ) {
                            Column(
                                modifier = Modifier.fillMaxHeight(50f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter =  painterResource(id = menu.icone),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxHeight(80f)
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = stringResource(id = menu.titulo),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class Menu(
    @StringRes val titulo: Int = R.string.string_vazia,
    val urlNavegacao: String = "em-construcao",
    @DrawableRes val icone: Int = R.drawable.baseline_do_disturb_24
)

@AndroidEntryPoint
class PreviewHomeClass : Fragment() {

    @ExperimentalLayoutApi
    @ExperimentalMaterial3Api
    @Preview(name = "Home Preview")
    @Preview(name = "Dark Home Preview", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun PreviewHome() {
        AnamneseDrAppTheme() {
            HomeOnCreate(rememberNavController(), hiltViewModel<MainViewModel>())
        }
    }
}