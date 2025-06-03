package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import com.nyth.app.core.model.remote.response.MetadataFormResponse
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CommunityPrivacyType(val type: String, val tag : String) : Parcelable {
    Public(type = "Public", tag = "Herkese Açık"),
    Private(type = "Private", tag = "Özel");

    companion object {
        fun toOptions(): List<MetadataFormResponse.Option> {
            return CommunityPrivacyType.values().map {
                MetadataFormResponse.Option(label = it.tag, value = it.type)
            }
        }
        fun getCommunityPrivacyTypeTag(tag: String): CommunityPrivacyType? {
            return CommunityPrivacyType.values().firstOrNull { it.tag == tag }
        }
    }
}
