package com.appsdeviser.component.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.appsdeviser.component.components.shimmer.animatedShimmer
import com.appsdeviser.component.core.AppTheme
import com.appsdeviser.component.core.Dimens

private object CustomTextFieldTokens {
    val MinHeight = Dimens.trackWidth
    val IconSize = Dimens.iconSizeSmall
    val BorderWidth = Dimens.borderExtraThin
    const val SINGLE_LINE = 1
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    variant: CustomTextFieldVariant,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    showValidation: Boolean = value.isNotEmpty(),
    validator: CustomFieldValidator? = DefaultFieldValidators.forVariant(variant),
    passwordVisible: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {},
    isLoading: Boolean = false,
    maxLines: Int = CustomTextFieldTokens.SINGLE_LINE,
) {
    val validation = validator?.validate(value) ?: CustomFieldValidationResult.Valid
    val errorMessage =
        (validation as? CustomFieldValidationResult.Invalid)
            ?.message
            ?.takeIf { showValidation }
    val hasError = errorMessage != null
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    val colors = AppTheme.colors
    val spacing = AppTheme.spacing
    val shape = AppTheme.shapes
    val borderColor =
        when {
            hasError -> colors.fieldBorderError
            focused -> colors.fieldBorderFocused
            else -> colors.fieldBorder
        }
    val textColor = if (enabled) colors.textPrimary else colors.textDisabled
    val keyboardType =
        when (variant) {
            CustomTextFieldVariant.Default -> KeyboardType.Text
            CustomTextFieldVariant.Email -> KeyboardType.Email
            CustomTextFieldVariant.PhoneNumber -> KeyboardType.Phone
            CustomTextFieldVariant.Password -> KeyboardType.Password
        }
    val visualTransformation =
        if (
            variant == CustomTextFieldVariant.Password && !passwordVisible
        ) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    Column(
        modifier = modifier,
    ) {
        BasicText(
            modifier = Modifier.animatedShimmer(isLoading = isLoading),
            text = label,
            style = AppTheme.typography.label.merge(TextStyle(color = colors.textPrimary)),
        )
        Spacer(Modifier.size(spacing.extraSmall))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = CustomTextFieldTokens.MinHeight)
                    .background(colors.container, shape.medium)
                    .border(CustomTextFieldTokens.BorderWidth, borderColor, shape.medium)
                    .semantics {
                        if (hasError) error(errorMessage)
                    }.animatedShimmer(isLoading = isLoading),
            enabled = enabled && !isLoading,
            singleLine = maxLines == CustomTextFieldTokens.SINGLE_LINE,
            maxLines = maxLines,
            interactionSource = interactionSource,
            textStyle = AppTheme.typography.body.merge(TextStyle(color = textColor)),
            cursorBrush = SolidColor(colors.fieldBorderFocused),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(start = spacing.medium, end = spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder.isNotEmpty()) {
                            BasicText(
                                modifier = Modifier.animatedShimmer(isLoading = isLoading),
                                text = placeholder,
                                style =
                                    AppTheme.typography.body.merge(
                                        TextStyle(color = colors.textSecondary),
                                    ),
                            )
                        }
                        innerTextField()
                    }
                    if (hasError) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = null,
                            tint = colors.textError,
                            modifier =
                                Modifier
                                    .size(CustomTextFieldTokens.IconSize)
                                    .animatedShimmer(isLoading = isLoading),
                        )
                    }
                    if (variant == CustomTextFieldVariant.Password) {
                        Spacer(Modifier.width(spacing.small))
                        val description = if (passwordVisible) "Hide password" else "Show password"
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = null,
                            tint = colors.textPrimary,
                            modifier =
                                Modifier
                                    .size(CustomTextFieldTokens.IconSize)
                                    .clickable(
                                        enabled = true,
                                        role = Role.Button,
                                        onClick = {
                                            onPasswordVisibilityChange(!passwordVisible)
                                        },
                                    ).clearAndSetSemantics {
                                        contentDescription = description
                                        role = Role.Button
                                    }.animatedShimmer(isLoading = isLoading),
                        )
                    }
                    Spacer(Modifier.width(spacing.small))
                }
            },
        )
        if (hasError) {
            BasicText(
                text = errorMessage,
                modifier =
                    Modifier
                        .padding(top = spacing.extraSmall)
                        .animatedShimmer(isLoading = isLoading),
                style = AppTheme.typography.supporting.merge(TextStyle(color = colors.textError)),
            )
        }
    }
}

// Custom TextField Preview Start //

@Preview
@Composable
private fun CustomTextFieldDefaultPreview() {
    AppTheme {
        CustomTextField(
            value = "",
            onValueChange = { },
            label = "Default — no validation",
            placeholder = "Any text is accepted",
            variant = CustomTextFieldVariant.Default,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldDefaultPreviewInDark() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomTextField(
            value = "",
            onValueChange = { },
            label = "Default — no validation",
            placeholder = "Any text is accepted",
            variant = CustomTextFieldVariant.Default,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldEmailInvalidPreviewInDark() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomTextField(
            value = "abc",
            onValueChange = { },
            label = "Email",
            placeholder = "incorrect email",
            variant = CustomTextFieldVariant.Email,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldPasswordValidPreview() {
    AppTheme {
        CustomTextField(
            value = "Abcd12345",
            onValueChange = { },
            label = "Password",
            placeholder = "",
            variant = CustomTextFieldVariant.Password,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldPasswordInValidPreviewInDark() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomTextField(
            value = "abcd12345",
            onValueChange = { },
            label = "Password",
            placeholder = "",
            variant = CustomTextFieldVariant.Password,
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldDefaultDisabledPreview() {
    AppTheme {
        CustomTextField(
            value = "",
            onValueChange = { },
            label = "Default — Disabled",
            placeholder = "Ui is Disabled",
            variant = CustomTextFieldVariant.Default,
            modifier =
                Modifier
                    .fillMaxWidth(),
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldDefaultLoadingPreview() {
    AppTheme {
        CustomTextField(
            value = "",
            onValueChange = { },
            label = "Default - Loading",
            placeholder = "Any text is accepted",
            variant = CustomTextFieldVariant.Default,
            modifier =
                Modifier
                    .fillMaxWidth(),
            isLoading = true,
        )
    }
}
// Custom TextField Preview End //
