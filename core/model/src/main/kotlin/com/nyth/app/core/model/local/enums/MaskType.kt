package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MaskType(val mask: String?, val reverse: Boolean) : Parcelable {
    Unknown(mask = null, reverse = false),
    TurkishDecimal(mask = "###.###.###.###.###.###.###.###.###.###", reverse = true),
    Currency(mask = "###.###.###.###.###.###.###.###.###.###", reverse = true),
    Percent(mask = "%##", reverse = false),
    FixedPoint(mask = null, reverse = false),
    Fraction(mask = null, reverse = false),
    Integer(mask = null, reverse = false);

    companion object {

    }
}