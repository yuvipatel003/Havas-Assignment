package com.appsdeviser.component.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.TextUnit

@Immutable
data class AppTextDimens(
    val extraSmallFontSize: TextUnit,
    val extraSmallFontLineHeight: TextUnit,
    val smallFontSize: TextUnit,
    val smallFontLineHeight: TextUnit,
    val smallMediumFontSize: TextUnit,
    val smallMediumLineHeight: TextUnit,
    val mediumFontSize: TextUnit,
    val mediumFontLineHeight: TextUnit,
    val largeFontSize: TextUnit,
    val largeFontLineHeight: TextUnit,
    val extraLargeFontSize: TextUnit,
    val extraLargeFontLineHeight: TextUnit,
)

internal fun getDefaultAppTextDimens(): AppTextDimens =
    AppTextDimens(
        extraSmallFontSize = Dimens.textExtraSmall,
        extraSmallFontLineHeight = Dimens.textExtraSmallLineHeight,
        smallFontSize = Dimens.textSmall,
        smallFontLineHeight = Dimens.textSmallLineHeight,
        smallMediumFontSize = Dimens.textSmallMedium,
        smallMediumLineHeight = Dimens.textSmallMediumLineHeight,
        mediumFontSize = Dimens.textMedium,
        mediumFontLineHeight = Dimens.textMediumLineHeight,
        largeFontSize = Dimens.textLarge,
        largeFontLineHeight = Dimens.textLargeLineHeight,
        extraLargeFontSize = Dimens.textExtraLarge,
        extraLargeFontLineHeight = Dimens.textExtraLargeLineHeight,
    )
