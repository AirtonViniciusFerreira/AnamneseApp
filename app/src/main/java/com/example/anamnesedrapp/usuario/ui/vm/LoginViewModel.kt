package com.example.anamnesedrapp.usuario.ui.vm

import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.anamnesedrapp.MainViewModel
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.usuario.service.UsuarioService
import com.example.anamnesedrapp.usuario.service.dto.UsuarioDTO
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
public class LoginViewModel @Inject constructor(
    val usuarioService: UsuarioService
) : ViewModel() {

    sealed class AutenticacaoEstado {
        object NaoAutenticado : AutenticacaoEstado()
        object Autenticado : AutenticacaoEstado()
        class AutenticacaoInvalida(val campos: List<Pair<String, Int>>) : AutenticacaoEstado()
        class AutenticacaoErro(val campos: List<Pair<String, String>>) : AutenticacaoEstado()
    }

    private lateinit var usuarioDTO: UsuarioDTO

//    private val autenticacaoEstadoEvento = MutableLiveData<AutenticacaoEstado>()

    private val autenticacaoEstadoMutable =
        MutableStateFlow<AutenticacaoEstado>(AutenticacaoEstado.NaoAutenticado)

    val AutenticacaoEstadoMutable: StateFlow<AutenticacaoEstado>
        get() = autenticacaoEstadoMutable.asStateFlow()

    val UsuarioDTO: UsuarioDTO
        get() = usuarioDTO

//    val AutenticacaoEstadoEvento: LiveData<AutenticacaoEstado>
//        get() = autenticacaoEstadoEvento

//    init {
//        recusarAutenticacao()
//    }

    fun recusarAutenticacao() {
//        autenticacaoEstadoEvento.value = AutenticacaoEstado.NaoAutenticado
        autenticacaoEstadoMutable.value = AutenticacaoEstado.NaoAutenticado
    }

    fun autenticar(nomeUsuario: String, senha: String) {
        if (!validarDados(nomeUsuario, senha)) {
            return
        }
        viewModelScope.launch {
            try {
                val usuarioDeferred = async { usuarioService.getUsuarioLogin(nomeUsuario, senha) }

                usuarioDeferred.await()
                    .fold(
                        onSuccess =  {
                            usuarioDTO = it
                            autenticacaoEstadoMutable.value = AutenticacaoEstado.Autenticado
                        },
                        onFailure =  {
                            val mensage = LOGIN_ERROS.first to (it.message ?: "")
                            val camposInvalidos = arrayListOf<Pair<String, String>>()
                            camposInvalidos.add(mensage)
                            autenticacaoEstadoMutable.value =
                                AutenticacaoEstado.AutenticacaoErro(camposInvalidos)
                        }
                    )

            } catch (expetion: Exception) {
                val mensage = LOGIN_ERROS.first to (expetion.message ?: "")
                val camposInvalidos = arrayListOf<Pair<String, String>>()
                camposInvalidos.add(mensage)
                autenticacaoEstadoMutable.value =
                    AutenticacaoEstado.AutenticacaoErro(camposInvalidos)
            }
        }

    }

    fun validarDados(nomeUsuario: String, senha: String): Boolean {
        val camposInvalidos = arrayListOf<Pair<String, Int>>()
        if (nomeUsuario.isNullOrEmpty() || nomeUsuario.isBlank()) {
            camposInvalidos.add(INPUT_NOMEUSUARIO)
        }
        if (senha.isNullOrEmpty() || senha.isBlank()) {
            camposInvalidos.add(INPUT_SENHA)
        }

        if (camposInvalidos.isNotEmpty()) {
//            autenticacaoEstadoEvento.value =
//                AutenticacaoEstado.AutenticacaoInvalida(camposInvalidos)
            autenticacaoEstadoMutable.value =
                AutenticacaoEstado.AutenticacaoInvalida(camposInvalidos)
            return false
        }
        return true
    }

    companion object {
        val INPUT_NOMEUSUARIO = "INPUT_NOMEUSUARIO" to R.string.login_campo_invalido_nomeusuario
        val INPUT_SENHA = "INPUT_SENHA" to R.string.login_campo_invalido_senha
        val LOGIN_ERROS = "LOGIN_ERROS" to ""
    }

}