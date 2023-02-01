package com.example.anamnesedrapp.geral.service.dto

import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum

data class PessoaDto(
    var id: Long = 0,
    var nomeCompletoRazaoSocial: String = "",
    var apelidoNomeFantasia: String = "",
    var cpfCnpj: String = "",
    var tipoPessoa: TipoPessoaEnum = TipoPessoaEnum.FISICO
)


fun PessoaDto.toPessoaEntity(): PessoaEntity {
    return with(this) {
        PessoaEntity(
            id = this.id,
            nomeCompletoRazaoSocial = this.nomeCompletoRazaoSocial,
            apelidoNomeFantasia = this.apelidoNomeFantasia,
            cpfCnpj = this.cpfCnpj,
            tipoPessoa =  this.tipoPessoa
        )
    }
}