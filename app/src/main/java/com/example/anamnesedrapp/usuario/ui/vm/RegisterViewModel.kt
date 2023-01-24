package com.example.anamnesedrapp.usuario.ui.vm

import androidx.lifecycle.ViewModel
import com.example.anamnesedrapp.usuario.service.UsuarioService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

class RegisterViewModel @Inject constructor(
    private var usuarioService: UsuarioService
) : ViewModel() {
    fun registrar() {
        println("teste")
    }

}