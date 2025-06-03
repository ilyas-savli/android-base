package com.nyth.app.core.model.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComparisonCompareResponse(
    @Json(name = "shareUrl")
    val shareUrl: String? = null,
    @Json(name = "motorcycles")
    val motorcycles: List<Motorcycle?>? = null,
    @Json(name = "metadataList")
    val metadataList: List<Metadata?>? = null
) {
    @JsonClass(generateAdapter = true)
    data class Motorcycle(
        @Json(name = "motorcycleId")
        val motorcycleId: String? = null,
        @Json(name = "brandName")
        val brandName: String? = null,
        @Json(name = "brandImageUrl")
        val brandImageUrl: String? = null,
        @Json(name = "modelName")
        val modelName: String? = null,
        @Json(name = "brandModelId")
        val brandModelId: String? = null,
        @Json(name = "rate")
        val rate: Int? = null,
        @Json(name = "voteCount")
        val voteCount: Int? = null,
        @Json(name = "imageUrl")
        val imageUrl: String? = null,
        @Json(name = "metadataGroup")
        val metadataGroup: List<MetadataGroup?>? = null,
        @Json(name = "editorReview")
        val editorReview: List<EditorReview?>? = null
    ) {
        @JsonClass(generateAdapter = true)
        data class MetadataGroup(
            @Json(name = "groupName")
            val groupName: String? = null,
            @Json(name = "metadata")
            val metadata: List<Metadata?>? = null
        ) {
            @JsonClass(generateAdapter = true)
            data class Metadata(
                @Json(name = "type")
                val type: String? = null,
                @Json(name = "value")
                val value: String? = null
            )
        }

        @JsonClass(generateAdapter = true)
        data class EditorReview(
            @Json(name = "editorFullname")
            val editorFullname: String? = null,
            @Json(name = "reviewText")
            val reviewText: String? = null,
            @Json(name = "rate")
            val rate: Int? = null
        )
    }

    @JsonClass(generateAdapter = true)
    data class Metadata(
        @Json(name = "groupName")
        val groupName: String? = null,
        @Json(name = "metadata")
        val metadata: List<String?>? = null
    )
}