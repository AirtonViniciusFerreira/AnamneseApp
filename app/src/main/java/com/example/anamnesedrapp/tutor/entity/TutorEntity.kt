package com.example.anamnesedrapp.tutor.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.anamnesedrapp.geral.entity.PessoaEntity
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.tutor.service.dto.TutorDto
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto

@Entity(
    tableName = "tutor",
    foreignKeys = [
        ForeignKey(
            entity = PessoaEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("pessoaId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class TutorEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val pessoaId: Long,
    val ativo: Boolean
)

fun TutorEntity.toTutorDto(): TutorDto {
    return TutorDto(
        id =  id,
        pessoaId = pessoaId,
        ativo = ativo
    )
}

fun TutorEntity.toTutorPessoaDto(): TutorPessoaDto {
    return TutorPessoaDto(
        id =  id,
        pessoa = PessoaDto(),
        ativo = ativo
    )
}

fun TutorEntity.toTutorPessoaDto(pessoaDto: PessoaDto): TutorPessoaDto {
    return TutorPessoaDto(
        id =  id,
        pessoa = pessoaDto,
        ativo = ativo
    )
}


