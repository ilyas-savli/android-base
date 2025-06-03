package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationsGroupByDateResponse(
    @Json(name = "notifications")
    val notifications: List<Notification>? = null,
    @Json(name = "date")
    val date: String? = null
) {
    @JsonClass(generateAdapter = true)
    data class Notification(
        @Json(name = "id")
        val id: String? = null,
        @Json(name = "notificationType")
        val notificationType: String? = null,
        @Json(name = "title")
        val title: String? = null,
        @Json(name = "body")
        val body: String? = null,
        @Json(name = "listing")
        val listing: Listing? = null,
        @Json(name = "message")
        val message : Message? = null,
        @Json(name = "sentDate")
        val sentDate: String? = null,
        @Json(name = "isChecked")
        val isChecked: Boolean? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class Listing(
            @Json(name = "id")
            val id: String? = null,
            @Json(name = "listingNumber")
            val listingNumber: Int? = null,
            @Json(name = "title")
            val title: String? = null,
            @Json(name = "price")
            val price: Double? = null,
            @Json(name = "oldPrice")
            val oldPrice: Double? = null,
            @Json(name = "currency")
            val currency: String? = null,
            @Json(name = "imageUrl")
            val imageUrl: String? = null,
            @Json(name = "location")
            val location: String? = null,
            @Json(name = "approvedAt")
            val approvedAt: String? = null,
            @Json(name = "metadata")
            val metadata: List<String?>? = null
        )
        @JsonClass(generateAdapter = true)
        data class Message(
            @Json(name = "conversationId")
            val conversationId: String? = null,
            @Json(name = "senderName")
            val senderName: String? = null,
            @Json(name = "message")
            val message: String? = null
        )
    }
}