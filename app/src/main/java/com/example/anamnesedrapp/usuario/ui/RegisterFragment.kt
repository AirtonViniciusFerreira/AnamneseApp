package com.example.anamnesedrapp.usuario.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloApp
import com.example.anamnesedrapp.usuario.ui.vm.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.ui.tooling.preview.Preview as Preview1

@AndroidEntryPoint
class RegisterFragment @Inject constructor(
    val registerViewModel: RegisterViewModel
) : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navHostController: NavHostController

    @Composable
    fun onCreate(navHostController: NavHostController) {
        this.navHostController = navHostController
        BaseTelaApp() {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TituloApp()
                Titulo()
                CreateForm()
            }
        }
    }

    @Composable
    private fun Titulo() {
        Text(text = stringResource(id = R.string.register_Titulo))
    }

    @Composable
    private fun CreateForm() {
        val sNomeUsuario = rememberSaveable { mutableStateOf("") }
        val sSenha = rememberSaveable { mutableStateOf("") }
        val bSenhaVisivel = rememberSaveable {
            mutableStateOf(false)
        }
        val pIcon =
            if (bSenhaVisivel.value)
                painterResource(id = R.drawable.ic_baseline_visibility_24)
            else
                painterResource(id = R.drawable.ic_baseline_visibility_off_24)
        OutlinedTextField(
            value = sNomeUsuario.value,
            onValueChange = {
                sNomeUsuario.value = it
            },
            label = { Text(text = stringResource(id = R.string.txt_login_label_login)) },
            placeholder = { Text(text = stringResource(id = R.string.txt_login_label_login)) },
            trailingIcon = {
                IconButton(
                    onClick = {
                        sNomeUsuario.value = ""
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
                        contentDescription = "Icone de Limpeza"
                    )
                }
            }
        )
        OutlinedTextField(
            value = sSenha.value,
            onValueChange = {
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
                        contentDescription = "Icone de Visulização"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (bSenhaVisivel.value) VisualTransformation.None else PasswordVisualTransformation()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                navHostController.navigate("login")
            }) {
                Text(text = stringResource(id = R.string.btn_login_entrar))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                registerViewModel.registrar()
            }) {
                Text(text = stringResource(id = R.string.btn_login_registrar))
            }
        }
    }

    @Composable
    @Preview1
    private fun preview() {
        onCreate(rememberNavController())
    }
}