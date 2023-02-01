package com.example.anamnesedrapp.tutor.ui.vp

import androidx.compose.runtime.MutableState
import com.example.anamnesedrapp.geral.ui.vp.PessoaCamposMutable
import com.example.anamnesedrapp.geral.ui.vp.toPessoaDto
import com.example.anamnesedrapp.tutor.service.dto.TutorPessoaDto

data class TutorCamposMutable(
    var id: MutableState<Long>,
    var pessoa: PessoaCamposMutable,
    var ativo: MutableState<Boolean>
)

fun TutorCamposMutable.toTutorPessoaDto():TutorPessoaDto {
    return with(this) {
        TutorPessoaDto(
            id = this.id.value,
            pessoa = this.pessoa.toPessoaDto(),
            ativo = this.ativo.value
        )
    }
}
