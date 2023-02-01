package com.example.anamnesedrapp.geral.ui.vp

import androidx.compose.runtime.MutableState
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.geral.service.dto.PessoaDto

data class PessoaCamposMutable(
    var id: MutableState<Long>,
    var nomeCompletoRazaoSocial: MutableState<String>,
    var apelidoNomeFantasia: MutableState<String>,
    var cpfCnpj: MutableState<String>,
    var tipoPessoa: MutableState<TipoPessoaEnum>
)

fun PessoaCamposMutable.toPessoaDto(): PessoaDto {
    return with(this) {
        PessoaDto(
            id = this.id.value,
            nomeCompletoRazaoSocial = this.nomeCompletoRazaoSocial.value,
            apelidoNomeFantasia = this.apelidoNomeFantasia.value,
            cpfCnpj = this.cpfCnpj.value,
            tipoPessoa = tipoPessoa.value
        )
    }
}
