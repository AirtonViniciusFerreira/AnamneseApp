package com.example.anamnesedrapp.geral.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.geral.ui.vp.PessoaCampoErros
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.BotaoIconeCancel
import com.example.anamnesedrapp.ui.util.BotaoIconeFlechaBaixo
import com.example.anamnesedrapp.ui.util.LabelOrPlaceholderText
import com.example.anamnesedrapp.util.ValidarCpfCnpj
import com.example.anamnesedrapp.util.ValidarNome


@ExperimentalMaterial3Api
@Composable
fun PessoaForm(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PessoaTipoPessoa(
            pessoaEdit = pessoaEdit,
            pessoaError = pessoaError
        )
        PessoaCpfCnpj(
            pessoaEdit = pessoaEdit,
            pessoaError = pessoaError
        )
        PessoaNome(
            pessoaEdit = pessoaEdit,
            pessoaError = pessoaError
        )
        PessoaSobreNome(
            pessoaEdit = pessoaEdit,
            pessoaError = pessoaError
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaTipoPessoa(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros
) {
    var expandido by remember { mutableStateOf(false) }
    var indeceSelecionado by remember { mutableStateOf(0) }
    var lstTiposPessoas: List<TipoPessoaEnum> = TipoPessoaEnum.values().toList()
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(5.dp)
//            .border(width = 2.dp, color = MaterialTheme.colorScheme.surface)
            .wrapContentSize(Alignment.TopStart)
    ) {
        OutlinedTextField(
            value = pessoaEdit.tipoPessoa.value.name,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandido = true },
            readOnly = true,
            label = { LabelOrPlaceholderText(idText = R.string.pessoa_label_tipoPessoa) },
            placeholder = { LabelOrPlaceholderText(idText = R.string.pessoa_label_tipoPessoa) },
            trailingIcon = {
                BotaoIconeFlechaBaixo(
                    onClick = {
                        expandido = true
                    },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            },
        )
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false },
            modifier = Modifier
                .fillMaxWidth(0.8F)
//                .padding(horizontal = 0.dp, vertical = 0.dp)
                .background(Color.Transparent)
        ) {
            lstTiposPessoas.forEachIndexed { index, tipoPessoaEnum ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = tipoPessoaEnum.name,
                        )
                    },
                    onClick = {
                        pessoaEdit.tipoPessoa.value = tipoPessoaEnum
                        indeceSelecionado = index
                        expandido = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                )
                Divider()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaCpfCnpj(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros,
) {
    @StringRes val textLabel: Int =
        if (pessoaEdit.tipoPessoa.value == TipoPessoaEnum.FISICO) R.string.pessoa_label_cpf
        else R.string.pessoa_label_cnpj
    OutlinedTextField(
        value = pessoaEdit.cpfCnpj.value,
        onValueChange = {
            if (it.isNotBlank() && pessoaError.cpfCnpjErro.value.isNotBlank())
                pessoaError.cpfCnpjErro.value = ""
            pessoaEdit.cpfCnpj.value = it
            pessoaError.cpfCnpjErro.value = ValidarCpfCnpj(pessoaEdit.cpfCnpj.value)
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = { LabelOrPlaceholderText(textLabel) },
        placeholder = { LabelOrPlaceholderText(textLabel) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                pessoaEdit.cpfCnpj.value = ""
            })
        },
        isError = pessoaError.cpfCnpjErro.value.isNotBlank(),
//        visualTransformation = MaskTransformationCpfCnpj(),
        keyboardOptions = KeyboardOptions(
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
@Composable
private fun PessoaNome(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros,
) {
    @StringRes val textLabel: Int =
        if (pessoaEdit.tipoPessoa.value == TipoPessoaEnum.FISICO) R.string.pessoa_label_nomeCompleto
        else R.string.pessoa_label_razaoSocial
    OutlinedTextField(
        value = pessoaEdit.nomeCompletoRazaoSocial.value,
        onValueChange = {
            if (it.isNotBlank() && pessoaError.nomeCompletoRazaoSocialErro.value.isNotBlank()) {
                pessoaError.nomeCompletoRazaoSocialErro.value = ""
            }
            pessoaEdit.nomeCompletoRazaoSocial.value = it
            pessoaError.nomeCompletoRazaoSocialErro.value =
                ValidarNome(pessoaEdit.nomeCompletoRazaoSocial.value)
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = { LabelOrPlaceholderText(textLabel) },
        placeholder = { LabelOrPlaceholderText(textLabel) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                pessoaEdit.nomeCompletoRazaoSocial.value = ""
            })
        },
        isError = pessoaError.nomeCompletoRazaoSocialErro.value.isNotBlank(),
        singleLine = true
    )
    if (pessoaError.nomeCompletoRazaoSocialErro.value.isNotBlank()) {
        Text(
            text = pessoaError.nomeCompletoRazaoSocialErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun PessoaSobreNome(
    pessoaEdit: PessoaCamposMutable,
    pessoaError: PessoaCampoErros,
) {
    @StringRes val textLabel: Int =
        if (pessoaEdit.tipoPessoa.value == TipoPessoaEnum.FISICO) R.string.pessoa_label_apelido
        else R.string.pessoa_label_nomeFantasia
    OutlinedTextField(
        value = pessoaEdit.apelidoNomeFantasia.value,
        onValueChange = {
            if (it.isNotBlank() && pessoaError.apelidoNomeFantasiaErro.value.isNotBlank())
                pessoaError.apelidoNomeFantasiaErro.value = ""
            pessoaEdit.apelidoNomeFantasia.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        label = { LabelOrPlaceholderText(textLabel) },
        placeholder = { LabelOrPlaceholderText(textLabel) },
        trailingIcon = {
            BotaoIconeCancel(onClick = {
                pessoaEdit.apelidoNomeFantasia.value = ""
            })
        },
        isError = pessoaError.apelidoNomeFantasiaErro.value.isNotBlank(),
        singleLine = true
    )
    if (pessoaError.apelidoNomeFantasiaErro.value.isNotBlank()) {
        Text(
            text = pessoaError.apelidoNomeFantasiaErro.value,
            color = MaterialTheme.colorScheme.error
        )
    }
}


@ExperimentalMaterial3Api
@Preview(name = "Pessoa Form Preview")
@Preview(name = "Dark Pessoa Form Preview", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewPessoaForm() {
    val pessoaEdit: PessoaCamposMutable = PessoaCamposMutable(
        id = rememberSaveable { mutableStateOf(0L) },
        nomeCompletoRazaoSocial = rememberSaveable { mutableStateOf("") },
        apelidoNomeFantasia = rememberSaveable { mutableStateOf("") },
        cpfCnpj = rememberSaveable { mutableStateOf("") },
        tipoPessoa = rememberSaveable { mutableStateOf(TipoPessoaEnum.FISICO) },
    )
    val pessoaError: PessoaCampoErros = PessoaCampoErros(
        nomeCompletoRazaoSocialErro = rememberSaveable { mutableStateOf("") },
        apelidoNomeFantasiaErro = rememberSaveable { mutableStateOf("") },
        cpfCnpjErro = rememberSaveable { mutableStateOf("") },
        tipoPessoaErro = rememberSaveable { mutableStateOf("") },
    )
    AnamneseDrAppTheme() {
        Column() {
            PessoaForm(
                pessoaEdit = pessoaEdit,
                pessoaError = pessoaError
            )
        }
    }
}
