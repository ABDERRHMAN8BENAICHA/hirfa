
package com.example.hirfa.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 🌞 Light Theme
private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    secondary = SecondaryWhite,
    tertiary = TertiaryBrightBlue,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = Color.White,
    onSecondary = PrimaryBlue,
    onBackground = TextDark,
    onSurface = TextDark,
    error = ErrorRed
)

// 🌙 Dark Theme
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkBlue,
    secondary = SecondaryDark,
    tertiary = TertiaryDarkBlue,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextLight,
    onSurface = TextLight,
    error = ErrorDarkRed
)

@Composable
fun HirfaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
