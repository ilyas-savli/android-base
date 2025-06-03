package com.nyth.app.core.model.local.enums

import com.nyth.app.core.model.remote.response.MetadataFormResponse

enum class Gender(var label: String) {
    FEMALE("Kadın"),
    MALE("Erkek"),
    NONE("Belirtmek istemiyorum");

    companion object {
        fun toMetadataFormResponse(genderList: List<Gender>): List<MetadataFormResponse.Option> {
            val list = genderList.map { MetadataFormResponse.Option(it.label, it.name) }
            return list.subList(0, list.size)
        }

        fun valueToName(value: String?) = Gender.values().firstOrNull { it.label == value } ?: NONE
        fun valueToGender(value: String?) = Gender.values().firstOrNull { it.name == value } ?: NONE

    }
}