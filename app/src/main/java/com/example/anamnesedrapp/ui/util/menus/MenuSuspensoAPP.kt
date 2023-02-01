package com.example.anamnesedrapp.ui.util.menus

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.geral.entity.TipoPessoaEnum
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@Composable
fun MenuSuspensoAPP(
    indeceSelecionado: MutableState<Int>,
    lstMenus: List<Any> = emptyList()
) {
    var expandido  by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            text = lstMenus[indeceSelecionado.value].toString(),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandido = true
                }
        )
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            lstMenus. forEachIndexed { index, any ->
                DropdownMenuItem(
                    onClick = {
                        indeceSelecionado.value = index
                        expandido = false
                    }
                ) {
                    Text(
                        text = any.toString(),
                    )
                }
            }
        }
    }
}

@Preview(name = "Menu Suspenso")
@Preview(name = "Dark Menu Suspenso", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewMenuSuspensoAPP(){
    var indeceSelecionado = remember { mutableStateOf(0) }
    AnamneseDrAppTheme {
        MenuSuspensoAPP(
            indeceSelecionado = indeceSelecionado,
            lstMenus = TipoPessoaEnum.values().toList()
        )
    }
}