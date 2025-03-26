package com.guilhermereisapps.rickandmortykotlin.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = SecondaryBlue,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextLight,
    onSurface = TextLight
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkGreenPrimary,
    secondary = SecondaryBlue,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextDark,
    onSurface = TextDark
)

private val HighContrastColorPalette = lightColorScheme(
    primary = PrimaryHighContrast,
    onPrimary = OnPrimaryHighContrast,
    primaryContainer = PrimaryVariantHighContrast,
    secondary = SecondaryHighContrast,
    onSecondary = OnSecondaryHighContrast,
    background = BackgroundHighContrast,
    onBackground = OnBackgroundHighContrast,
    surface = SurfaceHighContrast,
    onSurface = OnSurfaceHighContrast,
)

@Composable
fun RickAndMortyKotlinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrast: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isHighContrast -> HighContrastColorPalette
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
