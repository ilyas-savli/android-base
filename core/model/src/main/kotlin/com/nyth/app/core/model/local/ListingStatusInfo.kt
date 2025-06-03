package com.nyth.app.core.model.local

data class ListingStatusInfo(
    var firstStateMax: Int = 0,
    var firstStateFinish: Int = 0,
    var firstStageProgress: Float = 0f,
    var secondStateMax: Int = 0,
    var secondStateFinish: Int = 0,
    var secondStageProgress: Float = 0f,
    var thirdStateMax: Int = 0,
    var thirdStateFinish: Int = 0,
    var thirdStageProgress: Float = 0f,
)
