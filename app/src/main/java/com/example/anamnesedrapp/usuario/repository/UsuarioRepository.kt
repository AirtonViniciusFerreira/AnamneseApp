package com.example.anamnesedrapp.usuario.repository

import com.example.anamnesedrapp.usuario.entity.UsuarioEntity

interface UsuarioRepository {
    fun create(usuario: UsuarioEntity)

    fun findUsuarioById(id: Long): UsuarioEntity

    fun getUsuarioLogin(nomeUsuario: String, senha: String): UsuarioEntity

}