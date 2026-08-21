package com.appsdeviser.component.components.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration

internal data class CustomButtonStyle(
    val containerColor: Color,
    val contentColor: Color,
    val textDecoration: TextDecoration? = null,
    val borderColor: Color? = null,
)
