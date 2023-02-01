package com.example.anamnesedrapp.tutor.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.service.dto.PessoaDto
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto
import com.example.anamnesedrapp.tutor.ui.vm.TutorListState
import com.example.anamnesedrapp.tutor.ui.vm.TutorViewModel
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.*
import com.example.anamnesedrapp.ui.util.botoes.BotaoPreenchidoEditar
import com.example.anamnesedrapp.util.navegacao.tutorNavigateToTutorEdit
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Composable
fun TutorList(
    navHostController: NavHostController,
    tutorViewModel: TutorViewModel
) {
    BaseTelaApp(
        topbar = {
            TopAppBarCenter(R.string.tutor_titulo)
        },
        floatingActionButton = {
            BotaoFlutuanteAdd(
                onClick = {
                    navHostController.tutorNavigateToTutorEdit()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val lstAcoes: ArrayList<androidx.compose.material.SwipeableState<Int>> =
                    ArrayList()
                items(tutorViewModel.TutorListState.value.lstTutorPessoaDto) { tutor ->
//                items(tutorListState.value.lstTutorPessoaDto) { tutor ->
                    val swipeableInicial: Int = 1
                    val swipeableState: androidx.compose.material.SwipeableState<Int> =
                        rememberSwipeableState(
                            initialValue = swipeableInicial
                        )
                    lstAcoes.add(swipeableState)
                    if (swipeableState.isAnimationRunning && swipeableState.currentValue == 1) {
                        lstAcoes
                            .filter { it != swipeableState && it.currentValue == 0 }
                            .forEach {
                                val teste = it.currentValue
                                runBlocking {
                                    launch {
                                        if (it.currentValue == 0) {
                                            it.snapTo(targetValue = 1)
                                        }
                                    }
                                }
                            }
                    }
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        BoxAppListDeslize(
                            squareSize = LocalConfiguration.current.screenWidthDp.dp / 4,
                            swipeableInicial = swipeableInicial,
                            swipeableState = swipeableState,
                            headlineText = {
                                Column() {
                                    Text("nome: ${tutor.pessoa.nomeCompletoRazaoSocial}")
                                    Text("Cpf: ${tutor.pessoa.cpfCnpj}")
                                }
                            },
                            overlineText = {
                                Text(
                                    text = "Apelido: ${tutor.pessoa.apelidoNomeFantasia}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            trailingContent = {
                                BotaoPreenchidoEditar(
                                    onClick = {
                                        navHostController.tutorNavigateToTutorEdit(tutor.id.toString())
                                    }
                                )
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                headlineColor = MaterialTheme.colorScheme.onPrimary,
                                overlineColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

            }

        }
    }
}


val tutorListState = MutableStateFlow(
    TutorListState(
        loading = true,
        lstTutorPessoaDto = listOf(
            TutorPessoaDto(
                id = 1,
                pessoa = PessoaDto(id = 1, nomeCompletoRazaoSocial = "teste1", apelidoNomeFantasia = "teste", cpfCnpj = "123"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 2,
                pessoa = PessoaDto(id = 2, nomeCompletoRazaoSocial = "teste2", apelidoNomeFantasia = "teste", cpfCnpj = "124"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 3,
                pessoa = PessoaDto(id = 3, nomeCompletoRazaoSocial = "teste3", apelidoNomeFantasia = "teste", cpfCnpj = "125"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 4,
                pessoa = PessoaDto(id = 4, nomeCompletoRazaoSocial = "teste4", apelidoNomeFantasia = "teste", cpfCnpj = "126"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 5,
                pessoa = PessoaDto(id = 5, nomeCompletoRazaoSocial = "teste5", apelidoNomeFantasia = "teste", cpfCnpj = "127"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 6,
                pessoa = PessoaDto(id = 6, nomeCompletoRazaoSocial = "teste6", apelidoNomeFantasia = "teste", cpfCnpj = "128"),
                ativo = true
            ),
            TutorPessoaDto(
                id = 7,
                pessoa = PessoaDto(id = 7, nomeCompletoRazaoSocial = "teste7", apelidoNomeFantasia = "teste", cpfCnpj = "129"),
                ativo = true
            ),
        )
    )
)

@AndroidEntryPoint
class PreviewTutorListClass : Fragment() {

    //    val tutorViewModel:TutorViewModel by viewModels()
    @ExperimentalMaterialApi
    @ExperimentalLayoutApi
    @ExperimentalMaterial3Api
    @Preview(name = "Tutor List")
    @Preview(name = "Dark Tutor List", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun PreviewTutorList() {
        AnamneseDrAppTheme() {
            TutorList(
                navHostController = rememberNavController(),
                tutorViewModel = hiltViewModel<TutorViewModel>()
            )
//            TutorList(navHostController = rememberNavController(), tutorViewModel = tutorViewModel)
        }
    }
}