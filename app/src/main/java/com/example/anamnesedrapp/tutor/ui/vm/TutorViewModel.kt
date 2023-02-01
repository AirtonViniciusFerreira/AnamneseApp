package com.example.anamnesedrapp.tutor.ui.vm

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.tutor.service.TutorService
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TutorViewModel @Inject constructor(
    private val tutorService: TutorService
) : ViewModel() {
    private val tutorListState = MutableStateFlow(TutorListState(loading = true))
    private val tutorSaveState = MutableStateFlow(TutorSaveState(saveSucess = false, erro = ""))
    val TutorListState: StateFlow<TutorListState> = tutorListState.asStateFlow()
    val TutorSaveState: StateFlow<TutorSaveState> = tutorSaveState.asStateFlow()

    private val tutorSelecionado =
        tutorService.observeTutorSelecionado()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptySet()
            )

    init {
        autalizar()
    }

    private fun autalizar() {
        viewModelScope.launch {
            val tutorDeferred = async { tutorService.findAllWithPessoa() }

            tutorDeferred.await().fold(
                onSuccess = { lstTutor ->
                    tutorListState.update {
                        it.copy(
                            loading = false,
                            lstTutorPessoaDto = lstTutor
                        )
                    }
                },
                onFailure = { exception ->
                    tutorListState.update {
                        it.copy(
                            loading = false,
                            erro = exception.message ?: "Erro interno!"
                        )
                    }
                }
            )
        }
    }

    fun save(tutorPessoaDto: TutorPessoaDto) {
        viewModelScope.launch {
            lateinit var tutorDeferred: Deferred<Result<TutorPessoaDto>>
            if (tutorPessoaDto.id == 0L) {
                tutorDeferred = async { tutorService.create(tutorPessoaDto) }
            } else  {
                tutorDeferred = async { tutorService.update(tutorPessoaDto) }
            }

            tutorDeferred.await()
                .fold(
                    onSuccess = { tutorPessoaDto ->
                        tutorSaveState.update { tutorSaveState ->
                            tutorSaveState.copy(
                                saveSucess = true,
                                erro = "",
                                tutorSave = tutorPessoaDto
                            )
                        }
                    },
                    onFailure = { ex ->
                        tutorSaveState.update { tutorSaveState ->
                            tutorSaveState.copy(
                                saveSucess = false,
                                erro = ex.message!!,
                                tutorSave = null
                            )
                        }
                    }
                )
        }
    }

    fun mudarTutorSelecionado(tutorPessoaDto: TutorPessoaDto) {
        viewModelScope.launch {
            tutorService.mudarTutorSelecionado(tutorPessoaDto)
        }
    }

    fun mudarTutorSelecionado(tutorId: String) {
        viewModelScope.launch {
            val tutorDeferred = async { tutorService.findByIdWithPessoa(tutorId = tutorId.toLong()) }
            tutorDeferred.await()
                .fold(
                    onSuccess = {tutorPessoaDto ->
                        tutorSaveState.update { tutorSaveState ->
                            tutorSaveState.copy(
                                saveSucess = false,
                                tutorSave = tutorPessoaDto
                            )
                        }
                    }, onFailure =  {ex ->
                        tutorSaveState.update {tutorSaveState ->
                            tutorSaveState.copy(
                                saveSucess = false,
                                erro = ex.message.toString(),
                                tutorSave = null
                            )
                        }
                    }
                )
        }
    }

    fun getTutorSelecionado(): TutorPessoaDto {
        var tutorPessoaDto: TutorPessoaDto = TutorPessoaDto(pessoa = PessoaDto(), ativo = true)
        viewModelScope.launch {
            if (tutorSelecionado.value.isNotEmpty()) {
                tutorPessoaDto = tutorSelecionado.value.first()
            }
        }
        return tutorPessoaDto
    }
}

data class TutorListState(
    val lstTutorPessoaDto: List<TutorPessoaDto> = emptyList(),
    val loading: Boolean = false,
    val erro: String = "",
    val saveSucess: Boolean = false
)

data class TutorSaveState(
    val erro: String = "",
    val saveSucess: Boolean = false,
    val tutorSave: TutorPessoaDto? = null
)