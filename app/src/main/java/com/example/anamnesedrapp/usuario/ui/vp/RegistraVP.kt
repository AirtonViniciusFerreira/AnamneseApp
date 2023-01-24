package com.example.anamnesedrapp.usuario.ui.vp

import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO

data class RegistraVP(
    val nomeUsuario: String = "",
    val senha: String = "",
    val criadoPor: String
)