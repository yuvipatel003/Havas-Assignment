package com.appsdeviser.component.core

import androidx.compose.foundation.shape.RoundedCornerShape

data class AppShapes(
    val small: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val large: RoundedCornerShape,
)

internal fun getDefaultAppShapes(): AppShapes =
    AppShapes(
        small = RoundedCornerShape(Dimens.dp8),
        medium = RoundedCornerShape(Dimens.dp16),
        large = RoundedCornerShape(Dimens.dp24),
    )
