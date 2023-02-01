package com.example.anamnesedrapp.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtomBarApp(
    content: @Composable () -> Unit = {}
) {

}

@Preview(name = "Buttom Bar App")
@Preview(name = "Dark Buttom Bar App", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewButtomBarApp() {
    val list:List<String> = listOf("edit", "excliur", "deletar")
    ButtomBarApp(){

    }
}