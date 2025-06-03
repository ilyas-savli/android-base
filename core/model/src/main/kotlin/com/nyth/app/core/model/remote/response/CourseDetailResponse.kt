package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDetailResponse(
    @Json(name = "courseAccount")
    val courseAccount: CourseAccount?,
    @Json(name = "courseType")
    val courseType: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "discountedPrice")
    val discountedPrice: DiscountedPrice?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "isCertificated")
    val isCertificated: Boolean?,
    @Json(name = "isFree")
    val isFree: Boolean?,
    @Json(name = "lessons")
    val lessons: List<Lesson?>?,
    @Json(name = "price")
    val price: Price?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "totalDuration")
    val totalDuration: TotalDuration?,
    @Json(name = "trainer")
    val trainer: Trainer?,
    @Json(name = "videoCode")
    val videoCode: String?,
    @Json(name = "whatWillYouLearn")
    val whatWillYouLearn: List<String?>?
)

@JsonClass(generateAdapter = true)
data class CourseAccount(
    @Json(name = "id")
    val id: String?,
    @Json(name = "isCompleted")
    val isCompleted: Boolean?,
    @Json(name = "lastLessonTime")
    val lastLessonTime: LastLessonTime?
)

@JsonClass(generateAdapter = true)
data class DiscountedPrice(
    @Json(name = "amount")
    val amount: Int?,
    @Json(name = "currency")
    val currency: String?
)

@JsonClass(generateAdapter = true)
data class Duration(
    @Json(name = "hours")
    val hours: Int?,
    @Json(name = "minutes")
    val minutes: Int?,
    @Json(name = "seconds")
    val seconds: Int?
)

@JsonClass(generateAdapter = true)
data class Lesson(
    @Json(name = "duration")
    val duration: Duration?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "videoCode")
    val videoCode: String?
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

@JsonClass(generateAdapter = true)
data class LastLessonTime(
    @Json(name = "hours")
    val hours: Int?,
    @Json(name = "minutes")
    val minutes: Int?,
    @Json(name = "seconds")
    val seconds: Int?
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
data class Price(
    @Json(name = "amount")
    val amount: Int?,
    @Json(name = "currency")
    val currency: String?
)