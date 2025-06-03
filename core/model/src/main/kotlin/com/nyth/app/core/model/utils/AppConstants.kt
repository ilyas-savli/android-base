package com.nyth.app.core.model.utils

class AppConstants {
    companion object {
        /**
         * DATABASE CONFIGS
         */
        const val KEYSET_NAME = "master_keyset"
        const val PREFERENCE_FILE = "master_key_preference"
        const val MASTER_KEY_URI = "android-keystore://master_key"

        const val DATASTORE_FILE = "app_db.preferences_pb"
        const val ENCRYPTION_TYPE = "AES256_GCM"

        const val SCOPE = "androidbase profile openid offline_access"

        /**
         * app constants
         */
        const val NO_ERROR_VALIDATOR = -1
        const val PHONE_MASK_CHAR = '#'
        const val PHONE_MASK = "+90 (###) ### ## ##"
        const val PHONE_RAW_MASK = " (###) ### ## ##"
        const val PHONE_PREFIX = "+"
        const val PHONE_COUNTRY_TR_CODE = "90"
        const val WHATSAPP_URI_FORMAT = "https://api.whatsapp.com/send?phone=%s&text=%s"

        /**
         * Token request constants
         */
        const val providerGoogle = "google"
        const val scope = "androidbase profile openid offline_access"
        const val grantType = "External"
        const val clientId = "client"
        const val secret = "secret"
        const val privacyPolicyVersion = "v1    "

        val classifiedAdNumber = "classified_ad_number"
        val categoryType = "category_type"
        val categoryId = "category_id"
    }
}