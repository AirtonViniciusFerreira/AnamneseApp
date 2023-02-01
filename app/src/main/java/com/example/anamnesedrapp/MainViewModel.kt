package com.example.anamnesedrapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import com.example.anamnesedrapp.util.addOrRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
public class MainViewModel @Inject constructor(

) : ViewModel() {

    var appJustLaunched by mutableStateOf(false)
    var bUsuarioAutenticado by mutableStateOf(false)
    private var usuarioDTOLogado: MutableState<Set<UsuarioDTO>> = mutableStateOf(setOf<UsuarioDTO>())

    val UsuarioLogado: MutableState<Set<UsuarioDTO>>
        get() = usuarioDTOLogado


    fun login(usuarioDTO: UsuarioDTO) {
        bUsuarioAutenticado = true
        appJustLaunched = true
        usuarioDTOLogado.apply {
            this.value = setOf(usuarioDTO)
        }
    }

    fun logout() {
        bUsuarioAutenticado = false
        usuarioDTOLogado.apply {
            this.value = setOf<UsuarioDTO>()
        }
    }

}