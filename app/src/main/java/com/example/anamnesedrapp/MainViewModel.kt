package com.example.anamnesedrapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    var appJustLaunched by mutableStateOf(false)
    var bUsuarioAutenticado by mutableStateOf(false)
    private lateinit var usuarioDTOLogado: MutableState<UsuarioDTO>

    val UsuarioLogado: MutableState<UsuarioDTO>
        get() = usuarioDTOLogado

    fun login(usuarioDTO: UsuarioDTO) {
        bUsuarioAutenticado = true
        appJustLaunched = true
        usuarioDTOLogado = mutableStateOf(usuarioDTO)
    }

    fun logout() {
        bUsuarioAutenticado = false
    }

}