package com.appsdeviser.component.core

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalColors = staticCompositionLocalOf<AppColor> { getDefaultAppColor() }
private val LocalSpacing = staticCompositionLocalOf<AppSpacing> { getDefaultAppSpacing() }
private val LocalTypography = staticCompositionLocalOf<AppTypography> { getDefaultAppTypography() }
private val LocalShapes = staticCompositionLocalOf<AppShapes> { getDefaultAppShapes() }
private val LocalAppTextDimens = staticCompositionLocalOf<AppTextDimens> { getDefaultAppTextDimens() }

object AppTheme {
    val colors: AppColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val spacing: AppSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val appTextDimens: AppTextDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTextDimens.current
}

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    colors: AppColor =
        if (isDarkTheme) LocalAppColorDark else LocalAppColorLight,
    spacing: AppSpacing = getDefaultAppSpacing(),
    typography: AppTypography = getDefaultAppTypography(),
    shapes: AppShapes = getDefaultAppShapes(),
    appDimens: AppTextDimens = getDefaultAppTextDimens(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalSpacing provides spacing,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        LocalAppTextDimens provides appDimens,
    ) {
        content()
    }
}
