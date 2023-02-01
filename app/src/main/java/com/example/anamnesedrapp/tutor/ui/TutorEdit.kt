package com.example.anamnesedrapp.tutor.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.geral.ui.vp.PessoaCampoErros
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.tutor.service.dto.toTutorCamposMutable
import com.example.anamnesedrapp.tutor.ui.vm.TutorSaveState
import com.example.anamnesedrapp.tutor.ui.vm.TutorViewModel
import com.example.anamnesedrapp.tutor.ui.vp.TutorCampoErros
import com.example.anamnesedrapp.tutor.ui.vp.TutorCamposMutable
import com.example.anamnesedrapp.tutor.ui.vp.toTutorPessoaDto
import com.example.anamnesedrapp.ui.util.BaseTelaApp
import com.example.anamnesedrapp.ui.util.TituloFragment
import com.example.anamnesedrapp.ui.util.TopAppBarCenter
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun TutorEdit(
    navHostController: NavHostController,
    tutorViewModel: TutorViewModel,
    tutorId: String? = null
) {
//    val sNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val sNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val sSobreNome: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val sSobreNomeErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val sCpfCnpj: MutableState<String> = rememberSaveable { mutableStateOf("") }
//    val sCpfCnpjErro: MutableState<String> = rememberSaveable { mutableStateOf("") }
    val tutorError: TutorCampoErros = TutorCampoErros(
        pessoa = PessoaCampoErros(
            nomeCompletoRazaoSocialErro = rememberSaveable { mutableStateOf("") },
            apelidoNomeFantasiaErro = rememberSaveable { mutableStateOf("") },
            cpfCnpjErro = rememberSaveable { mutableStateOf("") },
            tipoPessoaErro = rememberSaveable { mutableStateOf("") },
        )
    )
    var tutorEdit: TutorCamposMutable = TutorCamposMutable(
        id = rememberSaveable { mutableStateOf(0L) },
        pessoa = PessoaCamposMutable(
            id = rememberSaveable { mutableStateOf(0L) },
            nomeCompletoRazaoSocial = rememberSaveable { mutableStateOf("") },
            apelidoNomeFantasia = rememberSaveable { mutableStateOf("") },
            cpfCnpj = rememberSaveable { mutableStateOf("") },
            tipoPessoa =  rememberSaveable { mutableStateOf(TipoPessoaEnum.FISICO) }
        ),
        ativo = rememberSaveable { mutableStateOf(true) },
    )
    var saveState: TutorSaveState = tutorViewModel.TutorSaveState.collectAsState().value
//    var tutor: TutorPessoaDto = TutorPessoaDto(pessoa = PessoaDto(), ativo = true)
    if (tutorId != null && tutorId.isNotBlank()) {
        tutorViewModel.mudarTutorSelecionado(tutorId)
        if (saveState.tutorSave != null) {
//            tutor = saveState.tutorSave!!
            tutorEdit = saveState.tutorSave!!.toTutorCamposMutable(tutorEdit)
//            sNome.value = tutor.pessoa.nome
//            sSobreNome.value = tutor.pessoa.sobreNome
//            sCpfCnpj.value = tutor.pessoa.cpfCpnj
        }
    }

    BaseTelaApp(
        topbar = {
            TopAppBarCenter(R.string.tutor_titulo)
        }
    ) {
        TituloFragment("Cadastrar um tutor")
//        PessoaEdit(
//            sNome = sNome,
//            sNomeErro = sNomeErro,
//            sSobreNome = sSobreNome,
//            sSobreNomeErro = sSobreNomeErro,
//            sCpfCnpj = sCpfCnpj,
//            sCpfCnpjErro = sCpfCnpjErro
//        ) {
        TutorForm(
            tutorEdit = tutorEdit,
            tutorError = tutorError
        ) {
            Row() {
                OutlinedButton(
                    onClick = {
                        navHostController.popBackStack()
                    }
                ) {
                    Text("Cancelar")
                }
                OutlinedButton(
                    onClick = {
//                        var pessoaDto: PessoaDto =
//                            PessoaDto(
//                                id = tutor.pessoa.id,
//                                nome = sNome.value,
//                                sobreNome = sSobreNome.value,
//                                cpfCpnj = sCpfCnpj.value
//                            )
//
//                        tutorViewModel.save(
//                            TutorPessoaDto(
//                                id = tutor.id,
//                                pessoa = pessoaDto,
//                                ativo = true
//                            )
//                        )
                          tutorViewModel.save(tutorEdit.toTutorPessoaDto())
                    },
                    enabled = errorStete(tutorError)
                ) {
                    Text("Gravar")
                }
            }

            saveState(
                navHostController = navHostController,
                saveState = saveState
            )
        }
    }
}

private fun errorStete(
    sNomeErro: MutableState<String>,
    sSobreNomeErro: MutableState<String>,
    sCpfCnpjErro: MutableState<String>
): Boolean {
    if (sNomeErro.value.isNotBlank() || sSobreNomeErro.value.isNotBlank() || sCpfCnpjErro.value.isNotBlank())
        return false
    return true
}

private fun errorStete(
    tutorError: TutorCampoErros
): Boolean {
    if (
        tutorError.pessoa.nomeCompletoRazaoSocialErro.value.isNotBlank() ||
        tutorError.pessoa.apelidoNomeFantasiaErro.value.isNotBlank() ||
        tutorError.pessoa.cpfCnpjErro.value.isNotBlank()
    ) return false
    return true
}

@Composable
private fun saveState(
    navHostController: NavHostController,
    saveState: TutorSaveState
) {
    when (saveState.saveSucess) {
        true -> {
            navHostController.popBackStack()
        }
        else -> {
            if (saveState.erro.isNotBlank()) {
                Text(text = "Erro: ${saveState.erro}")
            }
        }
    }
}

@AndroidEntryPoint
class TuTorCreatePreview : Fragment() {
    @ExperimentalLayoutApi
    @ExperimentalMaterial3Api
    @Preview(name = "Tutor Preview Create")
    @Preview(name = "Dark Tutor Preview Create", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun PreviewTutorCreate() {
        TutorEdit(
            navHostController = rememberNavController(),
            tutorViewModel = hiltViewModel<TutorViewModel>()
        )
    }
}