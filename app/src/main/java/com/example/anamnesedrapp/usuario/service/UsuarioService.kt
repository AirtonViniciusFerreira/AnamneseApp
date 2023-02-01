package com.example.anamnesedrapp.usuario.service


import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import com.example.anamnesedrapp.usuario.ui.vp.RegistraVP

interface UsuarioService {
    suspend fun create(usuario: RegistraVP)

    fun findUsuarioById(id: Long): UsuarioDTO

    suspend fun getUsuarioLogin(nomeUsuario: String, senha: String): Result<UsuarioDTO>
}