package com.appsdeviser.component.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.appsdeviser.component.components.button.ButtonTokens.MinTouchTarget
import com.appsdeviser.component.components.shimmer.animatedShimmer
import com.appsdeviser.component.core.AppColor
import com.appsdeviser.component.core.AppTheme
import com.appsdeviser.component.core.Dimens

private object ButtonTokens {
    val MinTouchTarget = Dimens.minTouchTarget
}

@Composable
fun CustomButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    variant: CustomButtonVariant = CustomButtonVariant.Filled,
    shape: RoundedCornerShape? = null,
    icon: (@Composable (contentColor: Color) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val visualState = resolveCustomButtonVisualState(enabled = enabled, pressed = pressed)
    val colors = AppTheme.colors
    val spacing = AppTheme.spacing
    val shapes = AppTheme.shapes
    val style =
        resolveCustomButtonStyle(
            variant = variant,
            state = visualState,
            colors = colors,
        )

    Row(
        modifier =
            modifier
                .animatedShimmer(isLoading = isLoading)
                .defaultMinSize(minHeight = MinTouchTarget)
                .clip(shape ?: shapes.small)
                .background(style.containerColor)
                .border(
                    Dimens.borderExtraThin,
                    style.borderColor ?: Color.Transparent,
                    shapes.small,
                ).semantics(mergeDescendants = true) { }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = enabled && !isLoading,
                    role = Role.Button,
                    onClick = onClick,
                ).padding(
                    horizontal = if (variant == CustomButtonVariant.Link) spacing.small else spacing.large,
                    vertical = spacing.medium,
                ),
        horizontalArrangement = Arrangement.spacedBy(spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.invoke(style.contentColor)
        BasicText(
            text = title,
            style =
                AppTheme.typography.button.merge(
                    TextStyle(
                        color = style.contentColor,
                        textDecoration = style.textDecoration,
                    ),
                ),
        )
    }
}

internal fun resolveCustomButtonStyle(
    variant: CustomButtonVariant,
    state: CustomButtonVisualState,
    colors: AppColor,
): CustomButtonStyle =
    when (variant) {
        CustomButtonVariant.Filled -> {
            when (state) {
                CustomButtonVisualState.Default -> {
                    CustomButtonStyle(
                        containerColor = colors.buttonPrimary,
                        contentColor = colors.buttonOnPrimary,
                        textDecoration = null,
                    )
                }

                CustomButtonVisualState.Pressed -> {
                    CustomButtonStyle(
                        containerColor = colors.buttonPrimaryPressed,
                        contentColor = colors.buttonOnPrimary,
                        textDecoration = null,
                    )
                }

                CustomButtonVisualState.Disabled -> {
                    CustomButtonStyle(
                        containerColor = colors.buttonDisabledContainer,
                        contentColor = colors.buttonDisableContent,
                        textDecoration = null,
                    )
                }
            }
        }

        CustomButtonVariant.Link -> {
            when (state) {
                CustomButtonVisualState.Default -> {
                    CustomButtonStyle(
                        containerColor = Color.Transparent,
                        contentColor = colors.buttonLink,
                        textDecoration = TextDecoration.Underline,
                    )
                }

                CustomButtonVisualState.Pressed -> {
                    CustomButtonStyle(
                        containerColor = Color.Transparent,
                        contentColor = colors.buttonLinkPressed,
                        textDecoration = TextDecoration.Underline,
                    )
                }

                CustomButtonVisualState.Disabled -> {
                    CustomButtonStyle(
                        containerColor = Color.Transparent,
                        contentColor = colors.buttonDisableContent,
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }
        }

        CustomButtonVariant.Outlined -> {
            when (state) {
                CustomButtonVisualState.Default -> {
                    CustomButtonStyle(
                        containerColor = Color.Transparent,
                        contentColor = colors.buttonOnSecondary,
                        textDecoration = null,
                        borderColor = colors.buttonSecondary,
                    )
                }

                CustomButtonVisualState.Pressed -> {
                    CustomButtonStyle(
                        containerColor = colors.buttonSecondary,
                        contentColor = colors.buttonOnSecondary,
                        textDecoration = null,
                        borderColor = colors.buttonSecondary,
                    )
                }

                CustomButtonVisualState.Disabled -> {
                    CustomButtonStyle(
                        containerColor = colors.buttonDisabledContainer,
                        contentColor = colors.buttonDisableContent,
                        textDecoration = null,
                        borderColor = colors.buttonSecondary,
                    )
                }
            }
        }
    }

// Custom Button Preview Start //

@Preview
@Composable
private fun ButtonFilledWithIconPreview() {
    AppTheme {
        CustomButton(
            title = "Button With Icon",
            onClick = { },
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
        )
    }
}

@Preview
@Composable
private fun ButtonFilledWithOutIconPreview() {
    AppTheme {
        CustomButton(
            title = "Button Without Icon",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun ButtonOutlinedWithIconPreview() {
    AppTheme {
        CustomButton(
            title = "Outlined Button With Icon",
            onClick = { },
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
            variant = CustomButtonVariant.Outlined,
        )
    }
}

@Preview
@Composable
private fun ButtonOutlinedWithoutIconPreview() {
    AppTheme {
        CustomButton(
            title = "Outlined Button Without Icon",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Outlined,
        )
    }
}

@Preview
@Composable
private fun ButtonLinkWithIconPreview() {
    AppTheme {
        CustomButton(
            title = "Outlined Button With Icon",
            onClick = { },
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
            variant = CustomButtonVariant.Link,
        )
    }
}

@Preview
@Composable
private fun ButtonLinkWithoutIconPreview() {
    AppTheme {
        CustomButton(
            title = "Outlined Button Without Icon",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Link,
        )
    }
}

@Preview
@Composable
private fun ButtonDisabledPreview() {
    AppTheme {
        CustomButton(
            title = "Disabled Button With Icon",
            onClick = { },
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
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun ButtonOutlinedDisabledPreview() {
    AppTheme {
        CustomButton(
            title = "Outlined Button Disabled",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Outlined,
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun ButtonLinkDisabledPreview() {
    AppTheme {
        CustomButton(
            title = "Link Button Disabled",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Link,
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun ButtonLoadingPreview() {
    AppTheme {
        CustomButton(
            title = "Button Loading",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Link,
            isLoading = true,
        )
    }
}

@Preview
@Composable
private fun ButtonFilledWithIconPreviewInDark() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomButton(
            title = "Button With Icon",
            onClick = { },
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
        )
    }
}

@Preview
@Composable
private fun ButtonOutlinedWithoutIconPreviewInDark() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomButton(
            title = "Outlined Button Without Icon",
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth(),
            variant = CustomButtonVariant.Outlined,
        )
    }
}

// Custom Button Preview End //
