package com.appsdeviser.component.components.switch

import androidx.compose.ui.graphics.Color

enum class CustomSwitchVisualState {
    On,
    Off,
    DisabledOn,
    DisabledOff,
}

internal fun resolveCustomSwitchVisualState(
    checked: Boolean,
    enabled: Boolean,
): CustomSwitchVisualState =
    when {
        enabled && checked -> CustomSwitchVisualState.On
        enabled -> CustomSwitchVisualState.Off
        checked -> CustomSwitchVisualState.DisabledOn
        else -> CustomSwitchVisualState.DisabledOff
    }

internal data class CustomSwitchStyle(
    val track: Color,
    val thumb: Color,
)
