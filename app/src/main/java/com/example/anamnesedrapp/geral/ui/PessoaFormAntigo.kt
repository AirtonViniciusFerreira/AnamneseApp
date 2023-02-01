package com.example.anamnesedrapp.geral.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.geral.ui.vp.PessoaCampoErros
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BotaoIconeCancel
import com.example.anamnesedrapp.ui.util.LabelOrPlaceholderText
import com.example.anamnesedrapp.util.ValidarCpfCnpj
import com.example.anamnesedrapp.util.ValidarNome

@ExperimentalMaterial3Api
@Composable
fun PessoaForm(
    sNome: MutableState<String>,
    sNomeErro: MutableState<String>,
    sSobreNome: MutableState<String>,
    sSobreNomeErro: MutableState<String>,
    sCpfCnpj: MutableState<String>,
    sCpfCnpjErro: MutableState<String>,
) {
    Column() {
        PessoaCpfCnpj(
            sCpfCnpj = sCpfCnpj,
            sCpfCnpjErro = sCpfCnpjErro,
        )
        PessoaNome(
            sNome = sNome,
            sNomeErro = sNomeErro,
        )
        PessoaSobreNome(
            sSobreNome = sSobreNome,
            sSobreNomeErro = sSobreNomeErro,
        )
    }
}


@ExperimentalMaterial3Api
@Composable
private fun PessoaNome(
    sNome: MutableState<String>,
    sNomeErro: MutableState<String>,
) {
    OutlinedTextField(
        value = sNome.value,
        onValueChange = {
            if (it.isNotBlank() && sNomeErro.value.isNotBlank()) {
                sNomeErro.value = ""
            }
            sNome.value = it
            sNomeErro.value = ValidarNome(sNome.value)
        },
        label = { LabelOrPlaceholderText(idText = R.string.pessoa_label_nomeCompleto) },
        placeholder = { LabelOrPlaceholderText(idText = R.string.pessoa_label_nomeFantasia) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                sNome.value = ""
            })
        },
        isError = sNomeErro.value.isNotBlank(),
        singleLine = true
    )
    if (sNomeErro.value.isNotBlank()) {
        Text(
            text = sNomeErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaSobreNome(
    sSobreNome: MutableState<String>,
    sSobreNomeErro: MutableState<String>,
) {
    OutlinedTextField(
        value = sSobreNome.value,
        onValueChange = {
            if (it.isNotBlank() && sSobreNomeErro.value.isNotBlank())
                sSobreNomeErro.value = ""
            sSobreNome.value = it
        },
        label = { LabelOrPlaceholderText(idText = R.string.pessoa_label_apelido) },
        placeholder = { LabelOrPlaceholderText(idText = R.string.pessoa_label_apelido) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                sSobreNome.value = ""
            })
        },
        isError = sSobreNomeErro.value.isNotBlank(),
        singleLine = true
    )
    if (sSobreNomeErro.value.isNotBlank()) {
        Text(
            text = sSobreNomeErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaCpfCnpj(
    sCpfCnpj: MutableState<String>,
    sCpfCnpjErro: MutableState<String>,
) {
    OutlinedTextField(
        value = sCpfCnpj.value,
        onValueChange = {
            if (it.isNotBlank() && sCpfCnpjErro.value.isNotBlank())
                sCpfCnpjErro.value = ""
            sCpfCnpj.value = it
            sCpfCnpjErro.value = ValidarCpfCnpj(sCpfCnpj.value)
        },
        label = { LabelOrPlaceholderText(R.string.pessoa_label_cpfCnpj) },
        placeholder = { LabelOrPlaceholderText(R.string.pessoa_label_cpfCnpj) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                sCpfCnpj.value = ""
            })
        },
        isError = sCpfCnpjErro.value.isNotBlank(),
//        visualTransformation = MaskTransformationCpfCnpj(),
        keyboardOptions =  KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
    )
    if (sCpfCnpjErro.value.isNotBlank()) {
        Text(
            text = sCpfCnpjErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaCpfCnpj(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros,
) {
    OutlinedTextField(
        value = pessoaEdit.cpfCnpj.value,
        onValueChange = {
            if (it.isNotBlank() && pessoaError.cpfCnpjErro.value.isNotBlank())
                pessoaError.cpfCnpjErro.value = ""
            pessoaEdit.cpfCnpj.value = it
            pessoaError.cpfCnpjErro.value = ValidarCpfCnpj(pessoaEdit.cpfCnpj.value)
        },
        label = { LabelOrPlaceholderText(R.string.pessoa_label_cpfCnpj) },
        placeholder = { LabelOrPlaceholderText(R.string.pessoa_label_cpfCnpj) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                pessoaEdit.cpfCnpj.value = ""
            })
        },
        isError = pessoaError.cpfCnpjErro.value.isNotBlank(),
//        visualTransformation = MaskTransformationCpfCnpj(),
        keyboardOptions =  KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true,
    )
    if (pessoaError.cpfCnpjErro.value.isNotBlank()) {
        Text(
            text = pessoaError.cpfCnpjErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}


@ExperimentalMaterial3Api
@Preview(name = "Pessoa Form Preview")
@Preview(name = "Dark Pessoa Form Preview", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewPessoaFormAntigo() {
    val sNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSobreNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sSobreNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sCpfCnpj: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val sCpfCnpjErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    AnamneseDrAppTheme() {
        Column() {
            PessoaForm(
                sNome = sNome,
                sNomeErro = sNomeErro,
                sSobreNome = sSobreNomeErro,
                sSobreNomeErro = sSobreNomeErro,
                sCpfCnpj = sCpfCnpj,
                sCpfCnpjErro = sCpfCnpjErro
            )
        }
    }
}
