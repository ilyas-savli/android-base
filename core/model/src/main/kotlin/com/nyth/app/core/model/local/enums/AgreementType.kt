package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class AgreementType(val type: String, val tag : String) : Parcelable {
    ProtectionProcessingPersonalData(type = "ProtectionProcessingPersonalData", tag = "KVKK"),
    PrivacyPolicy(type = "PrivacyPolicy", tag = "Gizlilik Sözleşmesi"),
    TermsOfUse(type = "TermsOfUse", tag = "Kullanım Koşulları"),
    BusinessUserAgreement(type = "BusinessUserAgreement", tag = "Kurumsal Kullanıcı Sözleşmesi"),
    CookiePolicy(type = "CookiePolicy", tag = "Çerez Politikası"),
    UserAgreement(type = "UserAgreement", tag = "Kullanıcı Sözleşmesi"),
    IysPermissionInformation(type = "IysPermissionInformation", tag = "Kullanıcı Sözleşmesi"),
    ContentPolicy(type = "ContentPolicy", tag = "İçerik Politikası"),
    ListingRules(type = "ListingRules", tag = "İlan Verme Kuralları"),
    DistanceSalesContract(type = "DistanceSalesContract", tag = "Mesafeli Satış Sözleşmesi"),
    NewsletterAgreement(type = "NewsletterAgreement", tag = "Bülten Sözleşmesi"),
    PreliminaryInformationForm(type = "PreliminaryInformationForm", tag = "Ön Bilgilendirme Formu");
}

fun getAgreementTypeByTag(tag: String): AgreementType? {
    return AgreementType.values().firstOrNull { it.tag == tag }
}