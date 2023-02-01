package com.example.anamnesedrapp.ui.util.checkbox

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme
import com.example.anamnesedrapp.R

@Composable
fun CaixaSelecaoAPP(
    checkedState: MutableState<Boolean>,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    @StringRes textId: Int,
    textModifier: Modifier = Modifier.padding(start = 16.dp),
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    val (iCheckedState, iOnStateChange) = checkedState
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = iCheckedState,
                onValueChange = { iOnStateChange(!iCheckedState) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = iCheckedState,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            colors = colors,
            interactionSource =  interactionSource
        )
        Text(
            text = stringResource(id = textId) ,
            style = textStyle,
            modifier = modifier
        )
    }
}

@Preview(name = "Caixa de Selecao")
@Preview(name = "Dark Caixa de Selecao", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewCaixaSelocaoAPP() {
    val check:  MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }
    AnamneseDrAppTheme {
        CaixaSelecaoAPP(checkedState = check, textId = R.string.app_name)
    }
}