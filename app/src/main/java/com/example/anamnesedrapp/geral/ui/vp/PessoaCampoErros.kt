package com.example.anamnesedrapp.geral.ui.vp

import androidx.compose.runtime.MutableState

data class PessoaCampoErros(
    var nomeCompletoRazaoSocialErro: MutableState<String>,
    var apelidoNomeFantasiaErro: MutableState<String>,
    var cpfCnpjErro: MutableState<String>,
    var tipoPessoaErro: MutableState<String>
)


