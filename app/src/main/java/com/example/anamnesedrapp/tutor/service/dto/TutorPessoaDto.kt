package com.example.anamnesedrapp.tutor.service.dto

import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.tutor.entity.TutorEntity
import com.example.anamnesedrapp.tutor.ui.vp.TutorCamposMutable

data class TutorPessoaDto(
    val id: Long = 0,
    val pessoa: PessoaDto,
    val ativo: Boolean
)

fun TutorPessoaDto.toTutorEntity(): TutorEntity {
    return TutorEntity(
        id = id,
        pessoaId = pessoa.id,
        ativo = ativo
    )
}

fun TutorPessoaDto.toTutorCamposMutable(tutorCamposMutable: TutorCamposMutable): TutorCamposMutable {
    with(this) {
        tutorCamposMutable.id.value = this.id
        tutorCamposMutable.ativo.value = this.ativo
        tutorCamposMutable.pessoa.id.value = this.pessoa.id
        tutorCamposMutable.pessoa.nomeCompletoRazaoSocial.value =
            this.pessoa.nomeCompletoRazaoSocial
        tutorCamposMutable.pessoa.apelidoNomeFantasia.value = this.pessoa.apelidoNomeFantasia
        tutorCamposMutable.pessoa.cpfCnpj.value = this.pessoa.cpfCnpj
    }
    return tutorCamposMutable
}