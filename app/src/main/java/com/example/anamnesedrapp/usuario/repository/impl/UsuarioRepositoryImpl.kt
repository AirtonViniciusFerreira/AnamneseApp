package com.example.anamnesedrapp.usuario.repository.impl

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.anamnesedrapp.usuario.entity.UsuarioEntity
import com.example.anamnesedrapp.usuario.repository.UsuarioRepository
import com.example.anamnesedrapp.usuario.repository.dao.UsuarioDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class UsuarioRepositoryImpl @Inject constructor(
   private val usuarioDao: UsuarioDao
) : UsuarioRepository {

    override fun create(usuario: UsuarioEntity) {
        runBlocking {
            launch {
                usuarioDao.save(usuario)
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun findUsuarioById(id: Long): UsuarioEntity {
        lateinit var usuario: UsuarioEntity
        runBlocking {
            launch {
                usuario = usuarioDao.findById(id)
            }
        }
        return usuario
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getUsuarioLogin(nomeUsuario: String, senha: String): UsuarioEntity {
        lateinit var usuario: UsuarioEntity
        runBlocking {
            launch {
                usuario = usuarioDao.getLogin(nomeUsuario, senha)
            }
        }
        return usuario
    }
}