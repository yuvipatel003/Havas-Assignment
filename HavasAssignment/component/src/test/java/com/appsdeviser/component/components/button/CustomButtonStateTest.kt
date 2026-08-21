package com.appsdeviser.component.components.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.appsdeviser.component.core.getDefaultAppColor
import org.junit.Assert.assertEquals
import org.junit.Test

class CustomButtonStateTest {
    @Test
    fun enabledAndNotPressed_resolvesDefault() {
        assertEquals(
            CustomButtonVisualState.Default,
            resolveCustomButtonVisualState(
                enabled = true,
                pressed = false,
            ),
        )
    }

    @Test
    fun enabledAndPressed_resolvesPressed() {
        assertEquals(
            CustomButtonVisualState.Pressed,
            resolveCustomButtonVisualState(
                enabled = true,
                pressed = true,
            ),
        )
    }

    @Test
    fun disabledAlwaysWinsOverPressed() {
        assertEquals(
            CustomButtonVisualState.Disabled,
            resolveCustomButtonVisualState(
                enabled = false,
                pressed = true,
            ),
        )
    }

    @Test
    fun filledVariantResolvesDefaultPressedAndDisabledTokens() {
        val colors = getDefaultAppColor()

        assertEquals(
            colors.buttonPrimary,
            resolveCustomButtonStyle(CustomButtonVariant.Filled, CustomButtonVisualState.Default, colors).containerColor,
        )
        assertEquals(
            colors.buttonPrimaryPressed,
            resolveCustomButtonStyle(CustomButtonVariant.Filled, CustomButtonVisualState.Pressed, colors).containerColor,
        )
        val disabled =
            resolveCustomButtonStyle(
                CustomButtonVariant.Filled,
                CustomButtonVisualState.Disabled,
                colors,
            )
        assertEquals(colors.buttonDisabledContainer, disabled.containerColor)
        assertEquals(colors.buttonDisableContent, disabled.contentColor)
    }

    @Test
    fun linkVariantHasNoContainerAndUsesUnderlinedStateColors() {
        val colors = getDefaultAppColor()
        val default =
            resolveCustomButtonStyle(
                CustomButtonVariant.Link,
                CustomButtonVisualState.Default,
                colors,
            )
        val pressed =
            resolveCustomButtonStyle(
                CustomButtonVariant.Link,
                CustomButtonVisualState.Pressed,
                colors,
            )

        assertEquals(Color.Transparent, default.containerColor)
        assertEquals(colors.buttonLink, default.contentColor)
        assertEquals(TextDecoration.Underline, default.textDecoration)
        assertEquals(colors.buttonPrimaryPressed, pressed.contentColor)
    }

    @Test
    fun outlinedVariantResolvesDefaultPressedAndDisabledTokens() {
        val colors = getDefaultAppColor()

        assertEquals(
            Color.Transparent,
            resolveCustomButtonStyle(CustomButtonVariant.Outlined, CustomButtonVisualState.Default, colors).containerColor,
        )
        assertEquals(
            colors.buttonSecondary,
            resolveCustomButtonStyle(CustomButtonVariant.Outlined, CustomButtonVisualState.Pressed, colors).containerColor,
        )
        val disabled =
            resolveCustomButtonStyle(
                CustomButtonVariant.Outlined,
                CustomButtonVisualState.Disabled,
                colors,
            )
        assertEquals(colors.buttonDisabledContainer, disabled.containerColor)
        assertEquals(colors.buttonDisableContent, disabled.contentColor)
    }
}
