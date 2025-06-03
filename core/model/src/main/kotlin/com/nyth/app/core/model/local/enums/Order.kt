package com.nyth.app.core.model.local.enums

enum class Order(val orderName: String) {
    DateAsc(orderName = "DateAsc"),
    DateDesc(orderName = "DateDesc"),
    AlphabeticalAsc(orderName = "AlphabeticalAsc"),
    AlphabeticalDesc(orderName = "AlphabeticalDesc"),
    PriceAsc(orderName = "PriceAsc"),
    PriceDesc(orderName = "PriceDesc")
}