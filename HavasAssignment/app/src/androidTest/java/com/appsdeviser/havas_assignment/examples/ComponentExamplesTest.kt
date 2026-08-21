package com.appsdeviser.havas_assignment.examples

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.appsdeviser.havas_assignment.MainActivity
import com.appsdeviser.havas_assignment.examples.ComponentExamplesTestingIdentifier.FILLED_BUTTON_WITH_ICON_PRESSED
import com.appsdeviser.havas_assignment.examples.ComponentExamplesTestingIdentifier.INTERACTIVE_SWITCH
import com.appsdeviser.havas_assignment.examples.ComponentExamplesTestingIdentifier.LINK_BUTTON_PRESSED
import com.appsdeviser.havas_assignment.examples.ComponentExamplesTestingIdentifier.OUTLINED_BUTTON_PRESSED
import com.appsdeviser.havas_assignment.examples.ComponentExamplesTestingIdentifier.THEME_SWITCH
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentExamplesTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun filledButtonInvokesCallerCallback() {
        composeRule.onNodeWithTag(FILLED_BUTTON_WITH_ICON_PRESSED).performClick()
        composeRule.onNodeWithText("Last action: Filled button tapped").assertIsDisplayed()
    }

    @Test
    fun outlinedButtonInvokesCallerCallback() {
        composeRule.onNodeWithTag(OUTLINED_BUTTON_PRESSED).performClick()
        composeRule.onNodeWithText("Last action: Outlined button tapped").assertIsDisplayed()
    }

    @Test
    fun linkButtonInvokesCallerCallback() {
        composeRule.onNodeWithTag(LINK_BUTTON_PRESSED).performClick()
        composeRule.onNodeWithText("Last action: Link button tapped").assertIsDisplayed()
    }

    @Test
    fun themeCanSwitchBetweenLightAndDark() {
        val darkInitially =
            composeRule
                .onAllNodesWithText("Dark Theme active")
                .fetchSemanticsNodes()
                .isNotEmpty()
        composeRule.onNodeWithTag(THEME_SWITCH).performClick()
        val expectedLabel = if (darkInitially) "Light Theme active" else "Dark Theme active"
        composeRule.onNodeWithText(expectedLabel).assertIsDisplayed()
    }

    @Test
    fun switchInvokesCallerCallback() {
        composeRule.onNodeWithTag(INTERACTIVE_SWITCH).performClick()
    }
}
