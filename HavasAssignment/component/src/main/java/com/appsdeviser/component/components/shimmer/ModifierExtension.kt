package com.appsdeviser.component.components.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import com.appsdeviser.component.core.AppTheme

@Composable
fun Modifier.animatedShimmer(
    isLoading: Boolean?,
    shape: RoundedCornerShape = AppTheme.shapes.medium,
): Modifier {
    if (isLoading == true) {
        val shimmerBrush = rememberShimmerBrush(isAnimated = true)
        return this
            .fillMaxWidth(1f)
            .clip(shape)
            .background(brush = shimmerBrush)
            .graphicsLayer {
                alpha = AlphaLevel.Alpha0.alpha
            }
    }
    return this
}

@Composable
private fun rememberShimmerBrush(isAnimated: Boolean): Brush {
    val colors = AppTheme.colors
    val shimmerColors =
        listOf(
            colors.shimmer.copy(alpha = AlphaLevel.Alpha100.alpha),
            colors.shimmer.copy(alpha = AlphaLevel.Alpha50.alpha),
            colors.shimmer.copy(alpha = AlphaLevel.Alpha100.alpha),
        )

    val transition = rememberInfiniteTransition()
    val translateAnim =
        transition.animateFloat(
            initialValue = ShimmerTokens.INITIAL_TRANSLATE,
            targetValue = ShimmerTokens.FINAL_TRANSLATE,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = ShimmerTokens.DURATION_MILLIS,
                            easing = ShimmerTokens.easing,
                        ),
                    repeatMode = ShimmerTokens.repeatMode,
                ),
        )

    return if (isAnimated) {
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(translateAnim.value, translateAnim.value),
            end = Offset(translateAnim.value + ShimmerTokens.FINAL_TRANSLATE, translateAnim.value + ShimmerTokens.FINAL_TRANSLATE),
        )
    } else {
        Brush.linearGradient(
            colors = shimmerColors,
            start = ShimmerTokens.startOffSet,
            end = ShimmerTokens.endOffSet,
        )
    }
}

private object ShimmerTokens {
    const val INITIAL_TRANSLATE = 0f
    const val FINAL_TRANSLATE = 1000f
    const val DURATION_MILLIS = 1300
    val easing = LinearEasing
    val repeatMode = RepeatMode.Restart
    val startOffSet = Offset(INITIAL_TRANSLATE, INITIAL_TRANSLATE)
    val endOffSet = Offset(FINAL_TRANSLATE, FINAL_TRANSLATE)
}
