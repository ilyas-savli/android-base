package com.nyth.app.core.designsystem.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.nyth.app.core.model.utils.AppConstants

class PhoneNumberTransformation(val countryCode: String = AppConstants.PHONE_COUNTRY_TR_CODE) :
    VisualTransformation {
    val prefix = AppConstants.PHONE_PREFIX
    val mask = prefix
        .plus(countryCode)
        .plus(AppConstants.PHONE_RAW_MASK)

    val maskNumber = AppConstants.PHONE_MASK_CHAR

    override fun filter(text: AnnotatedString): TransformedText {
        val cleanPhoneNumber = text.filter { it.isDigit() }
        val result = buildAnnotatedString {
            var index = 0
            for (ch in mask) {
                if (index >= cleanPhoneNumber.length) break

                if (ch == maskNumber) {
                    append(cleanPhoneNumber[index])
                    index++
                } else {
                    append(ch)
                }
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                var noneDigitCount = 0
                var i = 0
                while (i < offset + noneDigitCount) {
                    if (mask[i] != maskNumber)
                        noneDigitCount++
                    i++
                }
                return offset + noneDigitCount
            }

            override fun transformedToOriginal(offset: Int): Int =
                offset - mask.take(offset).count { it != maskNumber }
        }
        return TransformedText(result, offsetTranslator)
    }

    fun getChangedValue(text: String): String = text
        .removePrefix(prefix)
        .removePrefix(countryCode)
        .filter { it.isDigit() }
        .take(mask.count { it == maskNumber })

}