package com.example.anamnesedrapp.usuario.repository

import com.example.anamnesedrapp.usuario.entity.UsuarioEntity

interface UsuarioRepository {
    fun create(usuario: UsuarioEntity)

    fun findUsuarioById(id: Long): UsuarioEntity

    suspend fun getUsuarioLogin(nomeUsuario: String, senha: String): Result<UsuarioEntity>

}