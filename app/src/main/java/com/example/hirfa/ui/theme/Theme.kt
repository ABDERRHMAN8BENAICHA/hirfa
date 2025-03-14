package com.example.hirfa.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = CraftersGold,
    secondary = AmberAccent,
    tertiary = ToolboxNavy,
    background = Parchment,
    surface = Color.White,
    onPrimary = Graphite,
    onSecondary =  Color.White,
    onBackground = Graphite,
    onSurface = Graphite,
    error = SafetyRed,
)

private val DarkColorScheme = darkColorScheme(
    primary = ForgedBronze,
    secondary = WorkshopAmber,
    tertiary = MidnightOil,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    error = SafetyRed,
)

@Composable
fun HirfaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Choose color scheme based on theme mode
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}