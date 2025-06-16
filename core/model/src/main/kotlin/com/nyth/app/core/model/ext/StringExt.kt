package com.nyth.app.core.model.ext

object StringExt {
    val String.Companion.empty: String
        inline get() = ""
    val String.Companion.space: String
        inline get() = " "
    val String.Companion.requiredSymbol: String
        inline get() = "*"
    val String.Companion.slash: String
        inline get() = "/"
    val String.Companion.newLine: String
        inline get() = "\n"
    val String.Companion.dash: String
        inline get() = "-"
}