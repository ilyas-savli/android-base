package com.nyth.app.core.model.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "discountedPrice")
    val discountedPrice: DiscountedPrice? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "isFree")
    val isFree: Boolean? = null,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "price")
    val price: Price? = null,
    @Json(name = "startDate")
    val startDate: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "totalDuration")
    val totalDuration: TotalDuration? = null,
    @Json(name = "totalStudentCount")
    val totalStudentCount: Int? = null,
    @Json(name = "trainer")
    val trainer: Trainer? = null,
    @Json(name = "type")
    val type: String? = null
){
    @JsonClass(generateAdapter = true)
    data class DiscountedPrice(
        @Json(name = "amount")
        val amount: Int?,
        @Json(name = "currency")
        val currency: String?
    )
    @JsonClass(generateAdapter = true)
    data class Price(
        @Json(name = "amount")
        val amount: Int?,
        @Json(name = "currency")
        val currency: String?
    )
    @JsonClass(generateAdapter = true)
    data class TotalDuration(
        @Json(name = "hours")
        val hours: Int?,
        @Json(name = "minutes")
        val minutes: Int?,
        @Json(name = "seconds")
        val seconds: Int?
    )
    @JsonClass(generateAdapter = true)
    data class Trainer(
        @Json(name = "imageUrl")
        val imageUrl: String?,
        @Json(name = "name")
        val name: String?,
        @Json(name = "surname")
        val surname: String?
    )

}
