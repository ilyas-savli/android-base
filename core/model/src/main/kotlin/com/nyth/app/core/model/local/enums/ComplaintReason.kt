package com.nyth.app.core.model.local.enums

import com.nyth.app.core.model.remote.response.MetadataFormResponse

enum class ComplaintReason(val reasonTypeId: Int, val reason: String) {
    PRODUCT_SOLD(reasonTypeId = 1, "İlanda belirtilen ürün/hizmet satılmış"),
    WRONG_ADVERT_CATEGORY(reasonTypeId = 2, "İlan kategorisi hatalı"),
    WRONG_ADVERT_INFO(reasonTypeId = 3, "İlan bilgileri hatalı veya yanlış"),
    ADVERT_PUBLISHED_MULTIPLE(reasonTypeId = 4, "İlan birden fazla kere yayımlanmış"),
    CANNOT_REACH_ADVERTISER(reasonTypeId = 5, "İlan sahibine ulaşamıyorum"),
    UNSUITABLE(reasonTypeId = 6, "Uygunsuz"),
    OTHER(reasonTypeId = 7, "Diğer");

    companion object {
        fun toOptions(): List<MetadataFormResponse.Option> {
            return values().map {
                MetadataFormResponse.Option(label = it.reason, value = it.reasonTypeId.toString())
            }
        }
        fun fromReasonTypeId(reasonTypeId: Int): ComplaintReason? {
            return values().find { it.reasonTypeId == reasonTypeId }
        }
    }
}