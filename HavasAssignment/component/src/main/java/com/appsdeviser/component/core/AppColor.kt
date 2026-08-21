package com.appsdeviser.component.core

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.appsdeviser.component.core.Colors.Blue300
import com.appsdeviser.component.core.Colors.Blue700
import com.appsdeviser.component.core.Colors.Blue800
import com.appsdeviser.component.core.Colors.Blue900
import com.appsdeviser.component.core.Colors.Gray100
import com.appsdeviser.component.core.Colors.Gray300
import com.appsdeviser.component.core.Colors.Gray50
import com.appsdeviser.component.core.Colors.Gray600
import com.appsdeviser.component.core.Colors.Gray800
import com.appsdeviser.component.core.Colors.Gray900
import com.appsdeviser.component.core.Colors.Green300
import com.appsdeviser.component.core.Colors.Green700
import com.appsdeviser.component.core.Colors.Green800
import com.appsdeviser.component.core.Colors.Green900
import com.appsdeviser.component.core.Colors.Red200
import com.appsdeviser.component.core.Colors.Red800
import com.appsdeviser.component.core.Colors.Red950
import com.appsdeviser.component.core.Colors.Slate700
import com.appsdeviser.component.core.Colors.Slate800
import com.appsdeviser.component.core.Colors.Slate900
import com.appsdeviser.component.core.Colors.White

@Immutable
data class AppColor(
    // Background
    val background: Color,
    val surface: Color,
    val container: Color,
    // Text colors
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val textError: Color,
    val onError: Color,
    // Button colors
    val buttonPrimary: Color,
    val buttonPrimaryPressed: Color,
    val buttonOnPrimary: Color,
    val buttonSecondary: Color,
    val buttonSecondaryPressed: Color,
    val buttonOnSecondary: Color,
    val buttonLink: Color,
    val buttonLinkPressed: Color,
    val buttonDisabledContainer: Color,
    val buttonDisableContent: Color,
    // Borders
    val fieldBorder: Color,
    val fieldBorderFocused: Color,
    val fieldBorderError: Color,
    // Disabled
    val disabledContainer: Color,
    // Switch
    val switchOff: Color,
    val switchOn: Color,
    val switchDisabled: Color,
    val switchThumb: Color,
    val switchThumbDisabled: Color,
    // Shimmer
    val shimmer: Color,
)

internal val LocalAppColorLight: AppColor =
    AppColor(
        background = White,
        surface = White,
        container = Gray50,
        textPrimary = Gray900,
        textSecondary = Gray800,
        textDisabled = Gray600,
        textError = Red800,
        onError = White,
        buttonPrimary = Blue800,
        buttonPrimaryPressed = Blue900,
        buttonOnPrimary = White,
        buttonSecondary = Green800,
        buttonSecondaryPressed = Green900,
        buttonOnSecondary = Color.Black,
        buttonLink = Blue700,
        buttonLinkPressed = Blue900,
        buttonDisabledContainer = Gray100,
        buttonDisableContent = Gray600,
        fieldBorder = Gray600,
        fieldBorderFocused = Blue800,
        fieldBorderError = Red800,
        disabledContainer = Gray100,
        switchOff = Gray600,
        switchOn = Blue800,
        switchDisabled = Gray300,
        switchThumb = Color.White,
        switchThumbDisabled = Gray100,
        shimmer = Green700,
    )

internal val LocalAppColorDark: AppColor =
    AppColor(
        background = Slate900,
        surface = Slate900,
        container = Slate800,
        textPrimary = Gray50,
        textSecondary = Gray300,
        textDisabled = Gray600,
        textError = Red200,
        onError = Red950,
        buttonPrimary = Blue300,
        buttonPrimaryPressed = Gray50,
        buttonOnPrimary = Blue900,
        buttonSecondary = Green300,
        buttonSecondaryPressed = Gray50,
        buttonOnSecondary = Green900,
        buttonLink = Blue300,
        buttonLinkPressed = Gray50,
        buttonDisabledContainer = Slate700,
        buttonDisableContent = Gray300,
        fieldBorder = Gray300,
        fieldBorderFocused = Blue300,
        fieldBorderError = Red200,
        disabledContainer = Slate700,
        switchOff = Gray300,
        switchOn = Blue300,
        switchDisabled = Slate700,
        switchThumb = Blue900,
        switchThumbDisabled = Gray600,
        shimmer = Green700,
    )

internal fun getDefaultAppColor(): AppColor = LocalAppColorLight
