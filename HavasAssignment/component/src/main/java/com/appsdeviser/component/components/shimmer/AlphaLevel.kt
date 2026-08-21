package com.appsdeviser.component.components.shimmer

enum class AlphaLevel(
    val alpha: Float,
) {
    Alpha0(0f),
    Alpha10(0.1f),
    Alpha20(0.2f),
    Alpha30(0.3f),
    Alpha40(0.4f),
    Alpha50(0.5f),
    Alpha60(0.6f),
    Alpha70(0.7f),
    Alpha80(0.8f),
    Alpha90(0.9f),
    Alpha100(1f),
    ;

    companion object {
        fun fromAlpha(value: Float): AlphaLevel? = entries.find { it.alpha == value }
    }
}
