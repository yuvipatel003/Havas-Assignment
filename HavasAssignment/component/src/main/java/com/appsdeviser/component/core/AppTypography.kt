package com.appsdeviser.component.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Immutable
data class AppTypography(
    val button: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
    val supporting: TextStyle,
)

internal fun getDefaultAppTypography(): AppTypography =
    AppTypography(
        button =
            TextStyle(
                fontSize = getDefaultAppTextDimens().mediumFontSize,
                lineHeight = getDefaultAppTextDimens().mediumFontLineHeight,
                fontWeight = FontWeight.SemiBold,
            ),
        body =
            TextStyle(
                fontSize = getDefaultAppTextDimens().mediumFontSize,
                lineHeight = getDefaultAppTextDimens().mediumFontLineHeight,
                fontWeight = FontWeight.Normal,
            ),
        label =
            TextStyle(
                fontSize = getDefaultAppTextDimens().smallFontSize,
                lineHeight = getDefaultAppTextDimens().smallMediumLineHeight,
                fontWeight = FontWeight.Medium,
            ),
        supporting =
            TextStyle(
                fontSize = getDefaultAppTextDimens().smallFontSize,
                lineHeight = getDefaultAppTextDimens().smallFontLineHeight,
                fontWeight = FontWeight.Normal,
            ),
    )
