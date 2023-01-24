package com.example.anamnesedrapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
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
    onPrimary = APP_Primary_Blue_100_E,
    primaryContainer = APP_Primary_Blue_100_B,
    onPrimaryContainer = APP_Primary_Blue_100_A,
    secondary = APP_Secondary_Green_100,
    onSecondary = APP_Secondary_Green_100_A,
    secondaryContainer = APP_Secondary_Green_100_B,
    onSecondaryContainer = APP_Secondary_Green_100_D,
    tertiary = APP_tertiary_Orange_100,
    onTertiary = APP_Tertiary_Orange_100_B,
    tertiaryContainer = APP_Tertiary_Orange_100_D,
    onTertiaryContainer = APP_Tertiary_Orange_100_D,
    surface = APP_Primary_Blue_100_D,
    background = Black,
)

private val LightColorScheme = lightColorScheme(
//    primary = Purple_500,
//    secondary = Teal_200,
//    tertiary = Pink40,
    primary = APP_Primary_Blue_100,
    onPrimary = APP_Primary_Blue_100_E,
    primaryContainer = APP_Primary_Blue_100_B,
    onPrimaryContainer = APP_Primary_Blue_100_A,
    secondary = APP_Secondary_Green_100,
    onSecondary = APP_Secondary_Green_100_A,
    secondaryContainer = APP_Secondary_Green_100_B,
    onSecondaryContainer = APP_Secondary_Green_100_D,
    tertiary = APP_tertiary_Orange_100,
    onTertiary = APP_Tertiary_Orange_100_B,
    tertiaryContainer = APP_Tertiary_Orange_100_D,
    onTertiaryContainer = APP_Tertiary_Orange_100_D,
    surface = APP_Primary_Blue_100_D,
    background = White,


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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
}