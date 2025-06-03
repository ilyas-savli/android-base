package com.nyth.app.core.model.local.enums



enum class StateModuleType(val type: String, val value: String) {
    CLASSIFIELDAD("ClassifiedAd", "ClassifiedAd"),
    SOCIAL("Social", "Social");

    companion object {
        fun fromType(type: String?): StateModuleType {
            return values().firstOrNull { it.type == type } ?: CLASSIFIELDAD
        }
    }
}