package com.podium.technicalchallenge.screens.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = Color(0xFF4169e1),
    primaryVariant = Color(0xFF4188FD),
    secondary = Color(0xFF03DAC5)
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        content = content,
        colors = ColorPalette
    )
}

@Composable
fun MyAppTextFieldColors() = TextFieldDefaults.textFieldColors(
    textColor = Color.White,
    disabledTextColor = Color.White,
    cursorColor = Color.White,
    focusedLabelColor = Color.White,
    unfocusedLabelColor = Color.White
)