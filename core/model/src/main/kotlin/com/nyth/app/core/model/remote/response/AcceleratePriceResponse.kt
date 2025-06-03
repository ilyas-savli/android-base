package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcceleratePriceResponse(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "features")
    val features: List<String?>? = null,
    @Json(name = "isPopular")
    val isPopular: Boolean? = null,
    @Json(name = "acceleratePlace")
    val acceleratePlace: AcceleratePlace? = null,
    @Json(name = "monthlyPrice")
    val monthlyPrice: MonthlyPrice? = null,
    @Json(name = "weeklyPrice")
    val weeklyPrice: WeeklyPrice? = null
) {
    enum class AcceleratePlace(val value: String) {
        @Json(name = "MakeDifferent")
        MakeDifferent("MakeDifferent"),

        @Json(name = "ShowInExplore")
        ShowInExplore("ShowInExplore"),

        @Json(name = "ShowAtTop")
        ShowAtTop("ShowAtTop"),

        @Json(name = "Showcase")
        Showcase("Showcase"),

        @Json(name = "ShowInCategoryPage")
        ShowInCategoryPage("ShowInCategoryPage")
    }

    @JsonClass(generateAdapter = true)
    data class MonthlyPrice(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Int? = null
    )

    @JsonClass(generateAdapter = true)
    data class WeeklyPrice(
        @Json(name = "currency")
        val currency: String? = null,
        @Json(name = "amount")
        val amount: Double? = null
    )
}