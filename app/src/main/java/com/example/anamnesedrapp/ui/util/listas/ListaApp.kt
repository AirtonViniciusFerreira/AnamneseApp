package com.example.anamnesedrapp.ui.util.listas

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anamnesedrapp.ui.theme.AnamneseDrAppTheme

@ExperimentalMaterial3Api
@Composable
fun ListaApp(
    headlineText: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    overlineText: (@Composable () -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    colors: ListItemColors = ListItemDefaults.colors()
){
    ListItem(
        headlineText = headlineText,
        modifier = modifier,
        overlineText = overlineText,
        leadingContent = leadingContent,
        trailingContent =  trailingContent,
        colors = colors
    )
    Divider()
}

@ExperimentalMaterial3Api
@Preview(name = "Lista")
@Preview(name = "Dark Lista", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewListaApp() {
    AnamneseDrAppTheme {
        ListaApp()
    }
}