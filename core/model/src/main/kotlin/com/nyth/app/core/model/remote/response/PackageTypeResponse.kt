package com.nyth.app.core.model.remote.response

data class PackageTypeResponse(
    val id : String,
    val name : String,
    val price : String,
    val periodTime : String,
    val isPopular : Boolean = false,
    var status : Boolean = false
)
