package com.nyth.app.core.designsystem.components.base.shape

import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.nyth.app.core.designsystem.components.ArrowPosition

class BubbleShape(
    private val cornerRadius: Dp = 15.dp,
    private val marginSize: Dp = 8.dp,
    private val arrowPosition: ArrowPosition,
    private val borderWidth: Dp = 1.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val marginSizePx = with(density) { marginSize.toPx() }
        val borderWidthPx = with(density) { borderWidth.toPx() }
        val rectPath = Path().apply {
            addRoundRect(
                RoundRect(
                    left = marginSizePx + borderWidthPx,
                    top = borderWidthPx,
                    right = size.width - marginSizePx - borderWidthPx,
                    bottom = size.height - borderWidthPx - marginSizePx,
                    radiusX = cornerRadiusPx,
                    radiusY = cornerRadiusPx
                )
            )
        }

        val trianglePath = Path().apply {
            when (arrowPosition) {
                ArrowPosition.BOTTOM_CENTER -> {
                    moveTo(size.width / 2f - 30f, size.height - marginSizePx)
                    lineTo(size.width / 2f + 30f, size.height - marginSizePx)
                    lineTo(size.width / 2f, size.height)
                    close()
                }

                ArrowPosition.BOTTOM_LEFT -> {
                    moveTo(marginSizePx + cornerRadiusPx + 30f, size.height - marginSizePx)
                    lineTo(marginSizePx + cornerRadiusPx, size.height - marginSizePx)
                    lineTo(marginSizePx + cornerRadiusPx + 15f, size.height)
                    close()
                }

                ArrowPosition.BOTTOM_RIGHT -> {
                    moveTo(
                        size.width - marginSizePx - cornerRadiusPx - 30f,
                        size.height - marginSizePx
                    )
                    lineTo(size.width - marginSizePx - cornerRadiusPx, size.height - marginSizePx)
                    lineTo(size.width - marginSizePx - cornerRadiusPx - 15f, size.height)
                    close()
                }

                else -> {
                    moveTo(size.width / 2f - 30f, size.height - marginSizePx)
                    lineTo(size.width / 2f + 30f, size.height - marginSizePx)
                    lineTo(size.width / 2f, size.height)
                    close()
                }
            }
        }

        return Outline.Generic(Path().apply {
            addPath(rectPath)
            addPath(trianglePath)
        })
    }
}