package com.nyth.app.core.designsystem.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.nyth.app.core.model.utils.AppConstants.Companion.PHONE_COUNTRY_TR_CODE

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {
    private val maxLength = mask.count { it == maskNumber }
    override fun filter(text: AnnotatedString): TransformedText {
        var cleanPhoneNumber = text.filter { it.isDigit() }
        val result = buildAnnotatedString {
            var index = 0
            for (ch in mask) {
                if (index >= cleanPhoneNumber.length) break

                if (ch == maskNumber) {
                    append(cleanPhoneNumber[index])
                    index++
                } else if (ch.isDigit() && ch == cleanPhoneNumber[index]) {
                    append(cleanPhoneNumber[index])
                    index++
                } else {
                    append(ch)
                }
            }
        }

        return TransformedText(result, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            if (mask[i] != numberChar && !mask[i].isDigit())
                noneDigitCount++
            i++
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - mask.take(offset).count { it != numberChar && !it.isDigit() }
}