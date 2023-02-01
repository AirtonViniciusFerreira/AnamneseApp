package com.example.anamnesedrapp.usuario.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.APP_Primary_Blue_100
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.theme.White
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloApp
import com.example.anamnesedrapp.ui.util.TituloFragment
import com.example.anamnesedrapp.usuario.ui.vm.LoginViewModel

//private lateinit var navHostControllerLogin: NavController
//private lateinit var nomeUsuarioErro: MutableState<String>
//private lateinit var senhaErro: MutableState<String>
//private lateinit var sNomeUsuario: MutableState<String>
//private lateinit var sSenha: MutableState<String>
//private lateinit var bSenhaVisivel: MutableState<Boolean>
//private lateinit var loginViewModel: LoginViewModel
//private lateinit var mainViewModel: MainViewModel

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun LoginOnCreate(
    navHostController: NavHostController,
    loginVM: LoginViewModel,
    mainVM: MainViewModel
) {
//    loginViewModel =  loginVM
//    mainViewModel = mainVM
//    navHostControllerLogin = navHostController

    val sNomeUsuarioErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSenhaErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sNomeUsuario: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSenha: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val bSenhaVisivel: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) }
    val sErros: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val validacaoCampo =
        initValidacaoCampos(
            sNomeUsuarioErro = sNomeUsuarioErro,
            sSenhaErro = sSenhaErro,
            sErros = sErros
        )
    ObservarEventoAutenticacao(
        validacaoCampo,
        loginVM,
        mainVM
    )

    BaseTelaApp(
        scaffoldModifier = Modifier
            .fillMaxWidth(),
        surfaceModifier = Modifier
            .fillMaxWidth(),
        surfaceColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = APP_Primary_Blue_100,
                )
                .paint(
                    painterResource(id = R.mipmap.ic_logos),
                    alpha = 0.85f,
                    contentScale = ContentScale.FillBounds,
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloApp(
                color = White
            )
            Spacer(
                modifier = Modifier
                    .height(50.dp)
                    .background(Color.Blue)
            )
            TituloFragment(idTitulo = R.string.login_titulo, color = White)

            UsuarioForm(
                sNomeUsuario = sNomeUsuario,
                sNomeUsuarioErro = sNomeUsuarioErro,
                sSenha = sSenha,
                sSenhaErro = sSenhaErro,
                bSenhaVisivel = bSenhaVisivel
            ) {
                FormAcoes(
                    navHostController = navHostController,
                    loginViewModel = loginVM,
                    sNomeUsuario = sNomeUsuario,
                    sSenha = sSenha,
                    sErros = sErros
                )
            }
        }
    }

}


@ExperimentalMaterial3Api
@Composable
fun ObservarEventoAutenticacao(
    camposValidacao: Map<String, MutableState<String>>,
    loginViewModel: LoginViewModel,
    mainViewModel: MainViewModel
) {
    val state: LoginViewModel.AutenticacaoEstado =
        loginViewModel.AutenticacaoEstadoMutable.collectAsState().value
    when (state) {
        is LoginViewModel.AutenticacaoEstado.Autenticado -> {
            mainViewModel.login(loginViewModel.UsuarioDTO)
        }
        is LoginViewModel.AutenticacaoEstado.AutenticacaoInvalida -> {
            state.campos.forEach { campo ->
                camposValidacao[campo.first]?.value = stringResource(campo.second)
            }
            loginViewModel.recusarAutenticacao()
        }
        is LoginViewModel.AutenticacaoEstado.AutenticacaoErro -> {
            state.campos.forEach { campo ->
                camposValidacao[campo.first]?.value = campo.second
            }
            loginViewModel.recusarAutenticacao()
        }
        else -> {

        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun FormAcoes(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel,
    sNomeUsuario: MutableState<String>,
    sSenha: MutableState<String>,
    sErros: MutableState<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                navHostController.navigate("register")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = stringResource(id = R.string.btn_login_registrar),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            onClick = {
                loginViewModel.autenticar(sNomeUsuario.value, sSenha.value)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = stringResource(id = R.string.btn_login_entrar),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
    if (sErros.value.isNotBlank()) {
        Text(
            text = sErros.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}

private fun initValidacaoCampos(
    sNomeUsuarioErro: MutableState<String>,
    sSenhaErro: MutableState<String>,
    sErros: MutableState<String>
) = mapOf(
    LoginViewModel.INPUT_NOMEUSUARIO.first to sNomeUsuarioErro,
    LoginViewModel.INPUT_SENHA.first to sSenhaErro,
    LoginViewModel.LOGIN_ERROS.first to sErros
)

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Preview(name = "Login Preview")
@Preview(name = "Dark Login Preview", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewLogin() {
    AnamneseDrAppTheme() {
        LoginOnCreate(
            rememberNavController(),
            hiltViewModel<LoginViewModel>(),
            hiltViewModel<MainViewModel>()
        )
    }
}