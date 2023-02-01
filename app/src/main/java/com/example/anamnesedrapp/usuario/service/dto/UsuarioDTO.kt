package com.example.anamnesedrapp.usuario.service.dto

import java.util.Date

data class UsuarioDTO (
    var id: String,
    var nomeUsuario: String,
    var criadoPor: String = "system",
    var criadoData: Date,
    var modificadoPor: String = "system",
    var modificadoData: Date
)