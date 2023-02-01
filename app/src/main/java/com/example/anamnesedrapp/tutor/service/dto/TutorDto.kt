package com.example.anamnesedrapp.tutor.service.dto

import com.example.anamnesedrapp.tutor.entity.TutorEntity

data class TutorDto(
    val id: Long = 0,
    val pessoaId: Long,
    val ativo: Boolean
)

fun TutorDto.toTutorEntity(): TutorEntity {
    return TutorEntity(
        id = this.id,
        pessoaId = this.pessoaId,
        ativo = ativo
    )
}
