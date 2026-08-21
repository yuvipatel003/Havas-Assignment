package com.appsdeviser.component.components.button

enum class CustomButtonVisualState {
    Default,
    Pressed,
    Disabled,
}

internal fun resolveCustomButtonVisualState(
    enabled: Boolean,
    pressed: Boolean,
): CustomButtonVisualState =
    when {
        !enabled -> CustomButtonVisualState.Disabled
        pressed -> CustomButtonVisualState.Pressed
        else -> CustomButtonVisualState.Default
    }
