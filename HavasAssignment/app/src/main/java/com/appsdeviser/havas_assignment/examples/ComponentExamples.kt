package com.appsdeviser.havas_assignment.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.appsdeviser.component.components.button.CustomButton
import com.appsdeviser.component.components.button.CustomButtonVariant
import com.appsdeviser.component.components.switch.CustomSwitch
import com.appsdeviser.component.components.textfield.CustomTextField
import com.appsdeviser.component.components.textfield.CustomTextFieldVariant
import com.appsdeviser.component.core.AppTheme

@Composable
fun ComponentsExamples(
    isDarkTheme: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit,
) {
    var freeText by rememberSaveable { mutableStateOf("No validation is applied") }
    var accountCode by rememberSaveable { mutableStateOf("ACC-1234") }
    var email by rememberSaveable { mutableStateOf("invalid@invalid") }
    var phone by rememberSaveable { mutableStateOf("416-555") }
    var password by rememberSaveable { mutableStateOf("weak") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var interactiveSwitch by rememberSaveable { mutableStateOf(false) }
    var lastAction by rememberSaveable { mutableStateOf("None") }
    val spacing = AppTheme.spacing
    val colors = AppTheme.colors

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(colors.surface)
                .verticalScroll(rememberScrollState())
                .padding(spacing.large),
        verticalArrangement = Arrangement.spacedBy(spacing.extraLarge),
    ) {
        BasicText(
            text = "Components Examples",
            style =
                TextStyle(
                    color = colors.textPrimary,
                    fontSize = AppTheme.appTextDimens.extraLargeFontSize,
                    lineHeight = AppTheme.appTextDimens.extraLargeFontLineHeight,
                    fontWeight = FontWeight.Bold,
                ),
        )

        CustomSwitch(
            checked = isDarkTheme,
            onCheckedChange = onDarkThemeToggle,
            label = "Dark theme",
            supportingText = if (isDarkTheme) "Dark Theme active" else "Light Theme active",
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(ComponentExamplesTestingIdentifier.THEME_SWITCH),
        )

        Section(title = "Buttons") {
            BasicText(
                text = "Filled — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Continue",
                onClick = { lastAction = "Filled button tapped" },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.FILLED_BUTTON_WITH_ICON_PRESSED),
                icon = { contentColor ->
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = contentColor,
                    )
                },
            )
            BasicText(
                text = "Filled — disabled",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Unavailable",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
            )

            BasicText(
                text = "Outlined — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Outlined Button",
                onClick = { lastAction = "Outlined button tapped" },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.OUTLINED_BUTTON_PRESSED),
                variant = CustomButtonVariant.Outlined,
            )
            BasicText(
                text = "Outlined — disabled",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Unavailable",
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                variant = CustomButtonVariant.Outlined,
            )

            BasicText(
                text = "Link — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Learn more",
                onClick = { lastAction = "Link button tapped" },
                variant = CustomButtonVariant.Link,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.LINK_BUTTON_PRESSED),
            )
            BasicText(
                text = "Link — disabled",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Unavailable link",
                onClick = {},
                variant = CustomButtonVariant.Link,
                enabled = false,
            )
            BasicText(
                text = "Last action: $lastAction",
                style = AppTheme.typography.supporting.merge(TextStyle(color = colors.textSecondary)),
            )
        }

        Section(title = "Text fields") {
            CustomTextField(
                value = freeText,
                onValueChange = { freeText = it },
                label = "Default — no validation",
                placeholder = "Any text is accepted",
                variant = CustomTextFieldVariant.Default,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.DEFAULT_FIELD),
            )
            CustomTextField(
                value = accountCode,
                onValueChange = { accountCode = it },
                label = "Default — external validator",
                placeholder = "VALID-1234",
                variant = CustomTextFieldVariant.Default,
                validator = AccountCodeValidator,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.CUSTOM_VALIDATOR_FIELD),
            )
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "name@example.com",
                variant = CustomTextFieldVariant.Email,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.EMAIL_FIELD),
            )
            CustomTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Phone",
                placeholder = "416-555-0199",
                variant = CustomTextFieldVariant.PhoneNumber,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.PHONE_FIELD),
            )
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "8+ characters",
                variant = CustomTextFieldVariant.Password,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = it },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.PASSWORD_FIELD),
            )
        }

        Section(title = "Switches") {
            CustomSwitch(
                checked = interactiveSwitch,
                onCheckedChange = { interactiveSwitch = it },
                label = "Interactive switch",
                supportingText = "Tap to inspect both states",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.INTERACTIVE_SWITCH),
            )
            CustomSwitch(
                checked = true,
                onCheckedChange = {},
                label = "On",
                modifier = Modifier.fillMaxWidth(),
            )
            CustomSwitch(
                checked = false,
                onCheckedChange = {},
                enabled = false,
                label = "Disabled off",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag(ComponentExamplesTestingIdentifier.DISABLED_SWITCH),
            )
            CustomSwitch(
                checked = true,
                onCheckedChange = {},
                enabled = false,
                label = "Disabled on",
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Section(title = "Loading States") {
            BasicText(
                text = "Filled — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Continue",
                onClick = { lastAction = "Filled button tapped" },
                modifier =
                    Modifier
                        .fillMaxWidth(),
                icon = { contentColor ->
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        tint = contentColor,
                    )
                },
                isLoading = true,
            )
            BasicText(
                text = "Outlined — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Outlined Button",
                onClick = { lastAction = "Outlined button tapped" },
                modifier =
                    Modifier
                        .fillMaxWidth(),
                variant = CustomButtonVariant.Outlined,
                isLoading = true,
            )

            BasicText(
                text = "Link — default / pressed",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomButton(
                title = "Learn more",
                onClick = { lastAction = "Link button tapped" },
                variant = CustomButtonVariant.Link,
                isLoading = true,
            )
            BasicText(
                text = "Default — no validation text field",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomTextField(
                value = freeText,
                onValueChange = { freeText = it },
                label = "Default — no validation",
                placeholder = "Any text is accepted",
                variant = CustomTextFieldVariant.Default,
                modifier =
                    Modifier
                        .fillMaxWidth(),
                isLoading = true,
            )
            BasicText(
                text = "switch",
                style = AppTheme.typography.label.merge(TextStyle(color = AppTheme.colors.textSecondary)),
            )
            CustomSwitch(
                checked = interactiveSwitch,
                onCheckedChange = { interactiveSwitch = it },
                label = "Interactive switch",
                supportingText = "Tap to inspect both states",
                modifier =
                    Modifier
                        .fillMaxWidth(),
                isLoading = true,
            )
        }
        Spacer(Modifier.height(spacing.large))
    }
}

@Composable
private fun Section(
    title: String,
    content: @Composable () -> Unit,
) {
    val spacing = AppTheme.spacing
    val colors = AppTheme.colors
    val shape = AppTheme.shapes
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(colors.container, shape.large)
                .padding(spacing.large),
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
    ) {
        BasicText(
            text = title,
            style =
                TextStyle(
                    color = colors.textPrimary,
                    fontSize = AppTheme.appTextDimens.largeFontSize,
                    lineHeight = AppTheme.appTextDimens.largeFontLineHeight,
                    fontWeight = FontWeight.Bold,
                ),
        )
        content()
    }
}
