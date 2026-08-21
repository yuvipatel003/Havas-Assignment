package com.appsdeviser.component.components.switch

import com.appsdeviser.component.core.getDefaultAppColor
import org.junit.Assert.assertEquals
import org.junit.Test

class CustomSwitchStateTest {
    @Test
    fun resolvesEveryCheckedAndEnabledCombination() {
        assertEquals(
            CustomSwitchVisualState.Off,
            resolveCustomSwitchVisualState(
                checked = false,
                enabled = true,
            ),
        )
        assertEquals(
            CustomSwitchVisualState.On,
            resolveCustomSwitchVisualState(
                checked = true,
                enabled = true,
            ),
        )
        assertEquals(
            CustomSwitchVisualState.DisabledOff,
            resolveCustomSwitchVisualState(
                checked = false,
                enabled = false,
            ),
        )
        assertEquals(
            CustomSwitchVisualState.DisabledOn,
            resolveCustomSwitchVisualState(
                checked = true,
                enabled = false,
            ),
        )
    }

    @Test
    fun everyVisualStateResolvesItsSemanticTokens() {
        val colors = getDefaultAppColor()

        assertEquals(
            colors.switchOn,
            resolveSwitchStyle(CustomSwitchVisualState.On, colors).track,
        )
        assertEquals(
            colors.switchOff,
            resolveSwitchStyle(CustomSwitchVisualState.Off, colors).track,
        )
        val disabled = resolveSwitchStyle(CustomSwitchVisualState.DisabledOn, colors)
        assertEquals(colors.switchDisabled, disabled.track)
        assertEquals(colors.switchThumbDisabled, disabled.thumb)
    }
}
