package com.example.anamnesedrapp.usuario.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.ui.theme.White

@ExperimentalMaterial3Api
@Composable
fun UsuarioForm(
    sNomeUsuario: MutableState<String>,
    sNomeUsuarioErro: MutableState<String>,
    sSenha: MutableState<String>,
    sSenhaErro: MutableState<String>,
    bSenhaVisivel: MutableState<Boolean>,
    content: @Composable () -> Unit = {}
) {
    FormNomeUsuario(
        sNomeUsuario = sNomeUsuario,
        sNomeUsuarioErro = sNomeUsuarioErro
    )
    FormSenha(
        sSenha = sSenha,
        sSenhaErro = sSenhaErro,
        bSenhaVisivel = bSenhaVisivel
    )
    content()
}

@ExperimentalMaterial3Api
@Composable
private fun FormNomeUsuario(
    sNomeUsuario: MutableState<String>,
    sNomeUsuarioErro: MutableState<String>
) {
    OutlinedTextField(
        value = sNomeUsuario.value,
        onValueChange = {
            if (it.isNotBlank() && sNomeUsuarioErro.value.isNotBlank())
                sNomeUsuarioErro.value = ""
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
                    contentDescription = stringResource(id = R.string.icon_cancel_description),
                    tint = White
                )
            }
        },
        isError = sNomeUsuarioErro.value.isNotBlank(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = White,
            placeholderColor = White,
            disabledPlaceholderColor = White,
            focusedLabelColor = White,
            unfocusedLabelColor = White,
            unfocusedBorderColor = White,

            )
    )

    if (sNomeUsuarioErro.value.isNotBlank())
        Text(
            text = sNomeUsuarioErro.value,
            color = MaterialTheme.colorScheme.error
        )
}

@ExperimentalMaterial3Api
@Composable
private fun FormSenha(
    sSenha: MutableState<String>,
    sSenhaErro: MutableState<String>,
    bSenhaVisivel: MutableState<Boolean>
) {

    val pIcon =
        if (bSenhaVisivel.value)
            painterResource(id = R.drawable.ic_baseline_visibility_24)
        else
            painterResource(id = R.drawable.ic_baseline_visibility_off_24)

    OutlinedTextField(
        value = sSenha.value,
        onValueChange = {
            if (it.isNotBlank() && sSenhaErro.value.isNotBlank())
                sSenhaErro.value = ""
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
        isError = sSenhaErro.value.isNotBlank(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = White,
            placeholderColor = White,
            focusedLabelColor = White,
            unfocusedLabelColor = White,
            unfocusedBorderColor = White
        )
    )

    if (sSenhaErro.value.isNotBlank())
        Text(
            text = sSenhaErro.value,
            color = MaterialTheme.colorScheme.error
        )
}

