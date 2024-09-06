package com.amoronk.movemate.ui.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    onSecondary = SecondaryTextColor,
    tertiary = TertiaryColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundLightColor,
    onBackground = Color.Black,
    surface = SurfaceLight,
    onSurface = Color.Black,
    surfaceVariant = SurfaceLight,
    onSurfaceVariant = Color.Black,
    secondaryContainer = SecondaryColor,
    onSecondaryContainer = Color.White,
    outlineVariant = IconDefaultColor,
    error = ErrorColor,
    onError = OnErrorLightColor,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    onPrimary = PrimaryTextColor,
    secondary = SecondaryColor,
    onSecondary = PrimaryTextColor,
    tertiary = TertiaryColor,
    onTertiary = PrimaryTextColor,
    background = BackgroundDarkColor,
    onBackground = Color.White,
    surface = SurfaceDark,
    onSurface = Color.White,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = Color.White,
    secondaryContainer = SecondaryColor,
    onSecondaryContainer = Color.White,
    outlineVariant = IconDefaultColor,
    error = ErrorColor,
    onError = OnErrorColor,
)


@Composable
fun MoniePointChallengeTheme(
    theme: Int,
    content: @Composable () -> Unit
) {
    val isDarkTheme = isSystemInDarkTheme()
    val autoColors = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    val dynamicColors = if (supportsDynamicTheming()) {
        val context = LocalContext.current
        if (isSystemInDarkTheme()) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        autoColors
    }

    val colors = when (theme) {
        Theme.LIGHT_THEME.themeValue -> LightColorScheme
        Theme.DARK_THEME.themeValue -> DarkColorScheme
        Theme.MATERIAL_YOU.themeValue -> dynamicColors
        else -> autoColors
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.background,
            darkIcons = !isDarkTheme
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

// To be used to set the preferred theme inside settings
enum class Theme(
    val themeValue: Int
) {
    MATERIAL_YOU(
        themeValue = 12
    ),
    FOLLOW_SYSTEM(
        themeValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    ),
    LIGHT_THEME(
        themeValue = AppCompatDelegate.MODE_NIGHT_NO
    ),
    DARK_THEME(
        themeValue = AppCompatDelegate.MODE_NIGHT_YES
    );
}