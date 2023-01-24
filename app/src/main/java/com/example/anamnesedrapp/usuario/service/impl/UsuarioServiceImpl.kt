package com.example.anamnesedrapp.usuario.service.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity
import com.example.anamnesedrapp.usuario.entity.toUsuarioDTO
import com.example.anamnesedrapp.usuario.entity.toUsuarioEntity
import com.example.anamnesedrapp.usuario.repository.UsuarioRepository
import com.example.anamnesedrapp.usuario.service.UsuarioService
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import com.example.anamnesedrapp.usuario.ui.vp.RegistraVP
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.N)
class UsuarioServiceImpl @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : UsuarioService {

    override suspend fun create(registraVP: RegistraVP){
            var usuario: UsuarioEntity = registraVP.toUsuarioEntity()
            usuarioRepository.create(usuario)
    }

    override fun findUsuarioById(id: Long): UsuarioDTO {
        return usuarioRepository.findUsuarioById(id).toUsuarioDTO()
    }

    override suspend fun getUsuarioLogin(nomeUsuario: String, senha: String): UsuarioDTO {
        return usuarioRepository.getUsuarioLogin(nomeUsuario, senha).toUsuarioDTO()
    }

    fun teste(nomeUsuario: String, senha: String) = runBlocking {
        launch {
            usuarioRepository.getUsuarioLogin(nomeUsuario, senha)
        }
    }
}