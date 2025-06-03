package com.nyth.app.core.model.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactFormRequest(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "surname")
    val surname: String? = null,
    @Json(name = "email")
    val email: String? = null,
    @Json(name = "phone")
    val phone: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null
)