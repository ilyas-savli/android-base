package com.nyth.app.core.model.local.enums

import com.nyth.app.core.model.remote.response.MetadataFormResponse

enum class ComplaintFeedReason(val reasonTypeId: Int, val reason: String) {
    NUDITY_SEXUALITY(reasonTypeId = 1, "Çıplaklık ve cinsellik"),
    HATE_SPEECH(reasonTypeId = 2, "Nefret söylemi veya sembolleri"),
    DANGEROUS_BEHAVIOR(reasonTypeId = 3, "Şiddet veya tehlikeli davranışlar"),
    FRAUD(reasonTypeId = 4, "Sahtekarık veya dolandırıcılık"),
    BULLYING_HARASSMENT(reasonTypeId = 5, "Zorbalık veya taciz"),
    OTHER(reasonTypeId = 7, "Diğer");

    companion object {
        fun toOptions(): List<MetadataFormResponse.Option> {
            return values().map {
                MetadataFormResponse.Option(label = it.reason, value = it.reasonTypeId.toString())
            }
        }
        fun fromReasonTypeId(reasonTypeId: Int): ComplaintFeedReason? {
            return values().find { it.reasonTypeId == reasonTypeId }
        }
    }
}