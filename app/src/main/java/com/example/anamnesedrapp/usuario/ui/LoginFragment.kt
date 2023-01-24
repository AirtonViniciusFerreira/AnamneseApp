package com.example.anamnesedrapp.usuario.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.APP_Primary_Blue_100
import com.example.anamnesedrapp.ui.theme.APP_Secondary_Green_100
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.theme.White
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloApp
import com.example.anamnesedrapp.usuario.ui.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class LoginFragment @Inject constructor(
    val loginViewModel: LoginViewModel,
    val mainViewModel: MainViewModel
) : Fragment() {

    private lateinit var navHostController: NavController
    private lateinit var nomeUsuarioErro: MutableState<String>
    private lateinit var senhaErro: MutableState<String>
    private lateinit var sNomeUsuario: MutableState<String>
    private lateinit var sSenha: MutableState<String>
    private lateinit var bSenhaVisivel: MutableState<Boolean>

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun onCreate(navHostController: NavHostController) {
        nomeUsuarioErro = rememberSaveable { mutableStateOf("") }
        senhaErro = rememberSaveable { mutableStateOf("") }
        this.navHostController = navHostController

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
                Titulo()
                CreateForm()
            }
        }

    }

    @Composable
    private fun Titulo() {
        Text(
            text = stringResource(id = R.string.login_titulo),
            style = MaterialTheme.typography.displayMedium,
            color = White
        )
    }

    @Composable
    private fun CreateForm() {
        FormLogin()
        FormSenha()
        FormAcoes()
        val validacaoCampo = initValidacaoCampos()
        observarEventoAutenticacao(
            validacaoCampo,
            loginViewModel.AutenticacaoEstadoMutable.collectAsState().value
        )
    }

    @Composable
    private fun FormLogin() {
        sNomeUsuario = rememberSaveable { mutableStateOf("") }

        OutlinedTextField(
            value = sNomeUsuario.value,
            onValueChange = {
                if (it.isNotBlank() && nomeUsuarioErro.value.isNotBlank())
                    nomeUsuarioErro.value = ""
                sNomeUsuario.value = it
            },
            label = {
                Text(text = stringResource(id = R.string.txt_login_label_login))
            },
            placeholder = { Text(text = stringResource(id = R.string.txt_login_label_login)) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        sNomeUsuario.value = ""
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                        contentDescription = "Icone de Limpeza",
                        tint = White
                    )
                }
            },
            isError = nomeUsuarioErro.value.isNotBlank(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = White,
                placeholderColor = White,
                disabledPlaceholderColor = White,
                focusedLabelColor = White,
                unfocusedLabelColor = White,
                unfocusedBorderColor = White,

            )
        )

        if (nomeUsuarioErro.value.isNotBlank())
            Text(
                text = nomeUsuarioErro.value,
                color = MaterialTheme.colorScheme.error
            )
    }

    @Composable
    private fun FormSenha() {
        sSenha = rememberSaveable { mutableStateOf("") }
        bSenhaVisivel = rememberSaveable { mutableStateOf(false) }

        val pIcon =
            if (bSenhaVisivel.value)
                painterResource(id = R.drawable.ic_baseline_visibility_24)
            else
                painterResource(id = R.drawable.ic_baseline_visibility_off_24)

        OutlinedTextField(
            value = sSenha.value,
            onValueChange = {
                if (it.isNotBlank() && senhaErro.value.isNotBlank())
                    senhaErro.value = ""
                sSenha.value = it
            },
            label = { Text(text = stringResource(id = R.string.txt_login_label_senha)) },
            placeholder = { Text(text = stringResource(id = R.string.txt_login_label_senha)) },
            trailingIcon = {
                IconButton(onClick = {
                    bSenhaVisivel.value = !bSenhaVisivel.value
                }) {
                    Icon(
                        painter = pIcon,
                        contentDescription = stringResource(id = R.string.icon_visibility_descripton),
                        tint = White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (bSenhaVisivel.value) VisualTransformation.None else PasswordVisualTransformation(),
            isError = senhaErro.value.isNotBlank(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = White,
                placeholderColor = White,
                focusedLabelColor = White,
                unfocusedLabelColor = White,
                unfocusedBorderColor = White
            )
        )

        if (senhaErro.value.isNotBlank())
            Text(
                text = senhaErro.value,
                color = MaterialTheme.colorScheme.error
            )
    }

    @Composable
    private fun FormAcoes() {
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
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
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
    }

    @Composable
    fun observarEventoAutenticacao(
        camposValidacao: Map<String, MutableState<String>>,
        state: LoginViewModel.AutenticacaoEstado
    ) {
        when (state) {
            is LoginViewModel.AutenticacaoEstado.Autenticado -> {
//                mainViewModel.login(loginViewModel.UsuarioDTO)
                navHostController.navigate("home")
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

    private fun initValidacaoCampos() = mapOf(
        LoginViewModel.INPUT_NOMEUSUARIO.first to nomeUsuarioErro,
        LoginViewModel.INPUT_SENHA.first to senhaErro
    )

//    @RequiresApi(Build.VERSION_CODES.O)
//    @Preview
//    @Composable
//    fun PreviewLogin() {
//        AnamneseDrAppTheme() {
//            onCreate(rememberNavController())
//        }
//    }

}