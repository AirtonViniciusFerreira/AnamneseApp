package com.example.anamnesedrapp.tutor.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.ui.util.checkbox.CaixaSelecaoAPP
import com.example.anamnesedrapp.R
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.geral.ui.PessoaEdit
import com.example.anamnesedrapp.geral.ui.vp.PessoaCampoErros
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.tutor.ui.vp.TutorCampoErros
import com.example.anamnesedrapp.tutor.ui.vp.TutorCamposMutable

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun TutorForm(
    tutorEdit: TutorCamposMutable,
    tutorError: TutorCampoErros,
    contentAcoes: @Composable () -> Unit = {}
) {
    Row() {
        TutorId(
            tutorEdit = tutorEdit
        )
        TutorAtivo(
            tutorEdit = tutorEdit
        )
    }
    Column() {
        PessoaEdit(
            pessoaEdit = tutorEdit.pessoa,
            pessoaError = tutorError.pessoa,
            contentAcoes = contentAcoes
        )
    }
}

@Composable
fun TutorId(
    tutorEdit: TutorCamposMutable
) {
    if (tutorEdit.id.value != 0L) {
        Text(
            text = tutorEdit.id.value.toString(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun TutorAtivo(
    tutorEdit: TutorCamposMutable
) {
    val checkState: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
    CaixaSelecaoAPP(
        checkedState = checkState,
        textId = R.string.app_name
    )

}


@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Preview(name = "Tutor Form Preview")
@Preview(name = "Dark Tutor Form Preview", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TutorFormPreview() {
    var tutorEdit: TutorCamposMutable = TutorCamposMutable(
        id = rememberSaveable { mutableStateOf(0L) },
        pessoa = PessoaCamposMutable(
            id = rememberSaveable { mutableStateOf(0L) },
            nomeCompletoRazaoSocial = rememberSaveable { mutableStateOf("") },
            apelidoNomeFantasia = rememberSaveable { mutableStateOf("") },
            cpfCnpj = rememberSaveable { mutableStateOf("") },
            tipoPessoa = rememberSaveable { mutableStateOf(TipoPessoaEnum.FISICO) },
        ),
        ativo = rememberSaveable { mutableStateOf(true) },
    )
    val tutorErro: TutorCampoErros = TutorCampoErros(
        PessoaCampoErros(
            nomeCompletoRazaoSocialErro = rememberSaveable { mutableStateOf("")  },
            apelidoNomeFantasiaErro = rememberSaveable { mutableStateOf("")  },
            cpfCnpjErro = rememberSaveable { mutableStateOf("")  },
            tipoPessoaErro = rememberSaveable { mutableStateOf("")  }
        )
    )

    AnamneseDrAppTheme() {
        TutorForm(
            tutorEdit = tutorEdit,
            tutorError = tutorErro
        ) {

        }
    }
}