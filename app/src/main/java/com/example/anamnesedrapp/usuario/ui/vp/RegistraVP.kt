package com.example.anamnesedrapp.usuario.ui.vp

import androidx.room.ColumnInfo
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import java.util.Date

data class RegistraVP(
    var nomeUsuario: String = "",
    var senha: String = "",
    var criadoPor: String = "system",
    var criadoData: Date,
    var modificadoPor: String = "system",
    var modificadoData: Date
)