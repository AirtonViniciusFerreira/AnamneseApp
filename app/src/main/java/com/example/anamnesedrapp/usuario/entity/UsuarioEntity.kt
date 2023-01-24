package com.example.anamnesedrapp.usuario.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import com.example.anamnesedrapp.usuario.ui.vp.RegistraVP

@Entity(tableName = "usuario")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nomeUsuario: String,
    val senha: String,
    val criadoPor: String
)

fun RegistraVP.toUsuarioEntity(): UsuarioEntity {
    return with(this) {
        UsuarioEntity(
            nomeUsuario = this.nomeUsuario,
            senha =  this.senha,
            criadoPor = this.criadoPor
        )
    }
}

fun UsuarioEntity.toUsuarioDTO(): UsuarioDTO {
    return UsuarioDTO(
        id = this.id.toString(),
        nomeUsuario = this.nomeUsuario,
        criadoPor = this.criadoPor
    )
}