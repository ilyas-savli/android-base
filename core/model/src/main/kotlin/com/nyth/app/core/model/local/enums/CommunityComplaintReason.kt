package com.nyth.app.core.model.local.enums

import com.nyth.app.core.model.remote.response.MetadataFormResponse

enum class CommunityComplaintReason(val reasonTypeId: Int, val reason: String) {
    NUDITY_AND_SEXUALITY(reasonTypeId = 1, reason = "Çıplaklık ve cinsellik"),
    HATE_SPEECH_OR_ITS_SYMBOLS(reasonTypeId = 2, reason = "Nefret söylemi veya sembolleri"),
    VIOLENT_OR_DANGEROUS_BEHAVIOR(reasonTypeId = 3, reason = "Şiddet veya tehlikeli davranışlar"),
    FRAUD_OR_SCAM(reasonTypeId = 4, reason = "Sahtekarık veya dolandırıcılık"),
    BULLYING_OR_HARASSMENT(reasonTypeId = 5, reason = "Zorbalık veya taciz"),
    OTHER(reasonTypeId = 6, reason = "Diğer");

    companion object {
        fun toOptions(): List<MetadataFormResponse.Option> {
            return values().map {
                MetadataFormResponse.Option(label = it.reason, value = it.reasonTypeId.toString())
            }
        }
        fun fromReasonTypeId(reasonTypeId: Int): CommunityComplaintReason? {
            return values().find { it.reasonTypeId == reasonTypeId }
        }
    }
}