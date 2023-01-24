package com.example.anamnesedrapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.anamnesedrapp.R

val FontFamilyTitulo = FontFamily(
    Font(R.font.frscript)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override*/
    displayLarge = TextStyle(
        fontFamily = FontFamilyTitulo,
        fontWeight = FontWeight.W400,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
//    titleMedium = TextStyle(
//        fontFamily = FontFamilyTitulo,
//        fontWeight = FontWeight.Medium,
//        fontSize = 22.sp,
//        lineHeight = 28.sp,
//        letterSpacing = 0.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Medium,
//        fontSize = 11.sp,
//        lineHeight = 16.sp,
//        letterSpacing = 0.5.sp
//    )
)