package com.example.anamnesedrapp.geral.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.geral.ui.vp.PessoaCampoErros
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloFragment

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun PessoaEdit(
    sNome: MutableState<String>,
    sNomeErro: MutableState<String>,
    sSobreNome: MutableState<String>,
    sSobreNomeErro: MutableState<String>,
    sCpfCnpj: MutableState<String>,
    sCpfCnpjErro: MutableState<String>,
    contentAcoes: @Composable () -> Unit = {}
) {
    BaseTelaApp {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloFragment(idTitulo = R.string.pessoa_titulo)
            Spacer(modifier = Modifier.height(10.dp))
            PessoaForm(
                sNome = sNome,
                sNomeErro = sNomeErro,
                sSobreNome = sSobreNome,
                sSobreNomeErro = sSobreNomeErro,
                sCpfCnpj = sCpfCnpj,
                sCpfCnpjErro = sCpfCnpjErro
            )
            contentAcoes()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun PessoaEdit(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros,
    contentAcoes: @Composable () -> Unit = {}
) {
    BaseTelaApp {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TituloFragment(idTitulo = R.string.pessoa_titulo)
            Spacer(modifier = Modifier.height(10.dp))
            PessoaForm(
                pessoaEdit = pessoaEdit,
                pessoaError = pessoaError
            )
            contentAcoes()
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
@Preview(name = "Pessoa Fragment")
@Preview(name = "Dark Pessoa Fragment", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
fun PreviewPessoa() {
    val sNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSobreNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSobreNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sCpfCnpj: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sCpfCnpjErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    AnamneseDrAppTheme() {
        PessoaEdit(
            sNome = sNome,
            sNomeErro = sNomeErro,
            sSobreNome = sSobreNomeErro,
            sSobreNomeErro = sSobreNomeErro,
            sCpfCnpj = sCpfCnpj,
            sCpfCnpjErro = sCpfCnpjErro
        ) {

        }
    }
}