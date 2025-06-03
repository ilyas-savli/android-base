package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class EventEditRequest(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "startDate")
    val startDate: String? = null,
    @Json(name = "endDate")
    val endDate: String? = null,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "latitude")
    val latitude: String? = null,
    @Json(name = "longitude")
    val longitude: String? = null,
    @Json(name = "imageUrl")
    val imageUrl: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "communities")
    val communities: List<String?>? = null,
    @Json(name = "eventId")
    val eventId: String? = null
)