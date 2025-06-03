package com.nyth.app.core.designsystem.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NoEmojiTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val filteredText = text.text.filter { it.isLetterOrDigit() || it.isWhitespace() || it.isLetter() }
        val originalText = text.text

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return originalText.take(offset).count { it.isLetterOrDigit() || it.isWhitespace() || it.isLetter() }
            }

            override fun transformedToOriginal(offset: Int): Int {
                var count = 0
                var transformedOffset = 0
                for (char in originalText) {
                    if (char.isLetterOrDigit() || char.isWhitespace() || char.isLetter()) {
                        if (count == offset) break
                        transformedOffset++
                    }
                    count++
                }
                return transformedOffset
            }
        }

        return TransformedText(
            text = AnnotatedString(filteredText),
            offsetMapping = offsetMapping
        )
    }
}