package com.example.anamnesedrapp.geral.entity

import androidx.room.*
import com.example.anamnesedrapp.geral.service.dto.PessoaDto

@Entity(tableName = "pessoa")
@TypeConverters(TipoPessoaConverter::class)
data class PessoaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var nomeCompletoRazaoSocial: String,
    var apelidoNomeFantasia: String,
    var cpfCnpj: String,
    @ColumnInfo(
        defaultValue = "0"
    )
    var tipoPessoa: TipoPessoaEnum
)


fun PessoaEntity.toPessoaDto(): PessoaDto {
    return PessoaDto(
        id = this.id,
        nomeCompletoRazaoSocial = this.nomeCompletoRazaoSocial,
        apelidoNomeFantasia = this.apelidoNomeFantasia,
        cpfCnpj = this.cpfCnpj
    )
}