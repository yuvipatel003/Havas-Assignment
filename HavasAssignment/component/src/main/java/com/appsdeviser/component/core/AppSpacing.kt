package com.appsdeviser.component.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class AppSpacing(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
)

internal fun getDefaultAppSpacing(): AppSpacing =
    AppSpacing(
        extraSmall = Dimens.dp4,
        small = Dimens.dp8,
        medium = Dimens.dp12,
        large = Dimens.dp16,
        extraLarge = Dimens.dp24,
    )
