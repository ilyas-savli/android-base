package com.nyth.app.core.model.local

import android.graphics.drawable.PaintDrawable

data class SavedAdvert(
    val imgUrl : String? = null,
    val brand : String,
    val model : String,
    val year : String,
    val engineCapacity : String,
    val motorkm : String,
    val price : String,
    val locationCity : String
)
