package com.nyth.app.core.designsystem.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.nyth.app.core.model.ext.BooleanExt.safeGet
import com.nyth.app.core.model.local.enums.MaskType

private class NumberOffsetMapper(val mask: String?, val numberChar: Char?) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        mask?.let {
            var noneDigitCount = 0
            var i = 0
            while (i < offset + noneDigitCount) {
                if (mask[i] != numberChar && !mask[i].isDigit())
                    noneDigitCount++
                i++
            }
            return offset + noneDigitCount
        }
        return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        mask?.let {
            return offset - mask.take(offset).count { it != numberChar && !it.isDigit() }
        }
        return offset
    }
}