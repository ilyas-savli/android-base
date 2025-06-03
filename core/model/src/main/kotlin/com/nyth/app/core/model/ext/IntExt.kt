package com.nyth.app.core.model.ext

object IntExt {
    fun Int.formatNumber(): String {
        val numberString = this.toString()
        val formattedNumber = StringBuilder()

        val startIndex = if (numberString.length % 3 == 0) 3 else numberString.length % 3

        formattedNumber.append(numberString.substring(0, startIndex))

        var index = startIndex
        while (index < numberString.length) {
            formattedNumber.append('.')
            formattedNumber.append(numberString.substring(index, index + 3))
            index += 3
        }

        return formattedNumber.toString()
    }

    fun Int.toCurrencyFormat(): String {
        return String.format("%,d,00 TL", this)
    }
    fun Int.toPriceFormat(): String {
        return String.format("%,d TL", this)
    }
}