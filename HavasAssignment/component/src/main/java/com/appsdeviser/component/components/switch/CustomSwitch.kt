package com.appsdeviser.component.components.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.appsdeviser.component.components.shimmer.animatedShimmer
import com.appsdeviser.component.core.AppColor
import com.appsdeviser.component.core.AppTheme
import com.appsdeviser.component.core.Dimens

private object CustomSwitchTokens {
    val MinTouchTarget = Dimens.minTouchTarget
    val TrackWidth = Dimens.trackWidth
    val TrackHeight = Dimens.trackHeight
    val ThumbSize = Dimens.thumbSize
    val OffThumbOffset = Dimens.offThumbOffset
    val OnThumbOffset = Dimens.thumbSize
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    supportingText: String? = null,
    isLoading: Boolean = false,
) {
    val state = resolveCustomSwitchVisualState(checked = checked, enabled = enabled)
    val style = resolveSwitchStyle(state, AppTheme.colors)
    val animatedTrack by animateColorAsState(style.track, label = "CustomSwitchTrack")
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) CustomSwitchTokens.OnThumbOffset else CustomSwitchTokens.OffThumbOffset,
        label = "CustomSwitchThumb",
    )
    val colors = AppTheme.colors
    val spacing = AppTheme.spacing
    val shape = AppTheme.shapes
    val contentColor = if (enabled) colors.textPrimary else colors.buttonDisableContent

    Row(
        modifier =
            modifier
                .defaultMinSize(minHeight = CustomSwitchTokens.MinTouchTarget)
                .semantics(mergeDescendants = true) {
                    stateDescription = if (checked) "On" else "Off"
                }.toggleable(
                    value = checked,
                    enabled = enabled && !isLoading,
                    role = Role.Switch,
                    onValueChange = onCheckedChange,
                ).padding(vertical = spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(
                        width = CustomSwitchTokens.TrackWidth,
                        height = CustomSwitchTokens.TrackHeight,
                    ).background(animatedTrack, shape.large)
                    .animatedShimmer(isLoading = isLoading),
            contentAlignment = Alignment.CenterStart,
        ) {
            Box(
                modifier =
                    Modifier
                        .offset(x = thumbOffset)
                        .size(CustomSwitchTokens.ThumbSize)
                        .background(style.thumb, CircleShape),
            )
        }
        if (label != null || supportingText != null) {
            Spacer(Modifier.width(spacing.medium))
            Column {
                label?.let {
                    BasicText(
                        modifier =
                            Modifier
                                .animatedShimmer(isLoading = isLoading),
                        text = it,
                        style = AppTheme.typography.body.merge(TextStyle(color = contentColor)),
                    )
                }
                supportingText?.let {
                    BasicText(
                        modifier = Modifier.animatedShimmer(isLoading = isLoading),
                        text = it,
                        style =
                            AppTheme.typography.supporting.merge(
                                TextStyle(color = contentColor),
                            ),
                    )
                }
            }
        }
    }
}

internal fun resolveSwitchStyle(
    state: CustomSwitchVisualState,
    colors: AppColor,
): CustomSwitchStyle =
    when (state) {
        CustomSwitchVisualState.On -> CustomSwitchStyle(colors.switchOn, colors.switchThumb)

        CustomSwitchVisualState.Off -> CustomSwitchStyle(colors.switchOff, colors.switchThumb)

        CustomSwitchVisualState.DisabledOn,
        CustomSwitchVisualState.DisabledOff,
        -> CustomSwitchStyle(colors.switchDisabled, colors.switchThumbDisabled)
    }

// Custom Switch Preview Start //

@Preview
@Composable
private fun CustomSwitchLoadingPreview() {
    AppTheme {
        CustomSwitch(
            checked = true,
            onCheckedChange = { },
            label = "Interactive switch",
            supportingText = "Tap to inspect both states",
            modifier =
                Modifier
                    .fillMaxWidth(),
            isLoading = true,
        )
    }
}

@Preview
@Composable
private fun CustomSwitchWithSupportingTextCheckedPreview() {
    AppTheme(
        isDarkTheme = false,
    ) {
        CustomSwitch(
            checked = true,
            onCheckedChange = { },
            label = "Interactive switch checked",
            supportingText = "Tap to inspect both states",
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomSwitchUnCheckedPreview() {
    AppTheme(
        isDarkTheme = false,
    ) {
        CustomSwitch(
            checked = false,
            onCheckedChange = { },
            label = "Interactive switch unchecked",
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomSwitchUnCheckedDisabledPreview() {
    AppTheme(
        isDarkTheme = false,
    ) {
        CustomSwitch(
            checked = false,
            onCheckedChange = { },
            label = "Interactive switch unchecked",
            modifier =
                Modifier
                    .fillMaxWidth(),
            enabled = false,
        )
    }
}

@Preview
@Composable
private fun CustomSwitchWithSupportingTextCheckedPreviewInDarkTheme() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomSwitch(
            checked = true,
            onCheckedChange = { },
            label = "Interactive switch checked",
            supportingText = "Tap to inspect both states",
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomSwitchUnCheckedPreviewInDarkTheme() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomSwitch(
            checked = false,
            onCheckedChange = { },
            label = "Interactive switch unchecked",
            modifier =
                Modifier
                    .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun CustomSwitchUnCheckedDisabledPreviewInDarkTheme() {
    AppTheme(
        isDarkTheme = true,
    ) {
        CustomSwitch(
            checked = false,
            onCheckedChange = { },
            label = "Interactive switch unchecked",
            modifier =
                Modifier
                    .fillMaxWidth(),
            enabled = false,
        )
    }
}

// Custom Switch Preview End //
