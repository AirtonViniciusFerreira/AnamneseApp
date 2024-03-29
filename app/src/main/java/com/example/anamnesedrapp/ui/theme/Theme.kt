package com.example.anamnesedrapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
//    primary = Purple_500,
//    secondary = Teal_200,
//    tertiary = Pink80,
//    surface = Light_blue_600,
    primary = APP_Primary_Blue_100,
    secondary = APP_Secondary_Green_100,
    tertiary = APP_Danger_Orange_100,
    surface = APP_Secondary_Blue_100,
    background = Black,
    onPrimary = Black,
    onSecondary = White

)

private val LightColorScheme = lightColorScheme(
//    primary = Purple_500,
//    secondary = Teal_200,
//    tertiary = Pink40,
    primary = APP_Primary_Blue_100,
    secondary = APP_Secondary_Green_100,
    tertiary = APP_Danger_Orange_100,
    surface = APP_Secondary_Blue_100,
    background = White,
    onPrimary = White,
    onSecondary = Black


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)


val AppDarkColors = darkColors(
    primary = APP_Primary_Blue_100,
    primaryVariant = APP_Secondary_Green_100,
    secondary = APP_Danger_Orange_100,
    surface = APP_Secondary_Blue_100,
    background = Black,
    onPrimary = Black,
    onSecondary = White
    // M2 dark Color parameters
)

val AppLightColors = lightColors(
    primary = APP_Primary_Blue_100,
    primaryVariant = APP_Secondary_Green_100,
    secondary = APP_Danger_Orange_100,
    surface = APP_Secondary_Blue_100,
    background = White,
    onPrimary = White,
    onSecondary = Black
    // M2 light Color parameters
)

@Composable
fun AnamneseDrAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val appColors  = if (darkTheme) {
        AppDarkColors
    } else {
        AppLightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
    androidx.compose.material.MaterialTheme(
        colors = appColors,
        typography = AppTypography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}