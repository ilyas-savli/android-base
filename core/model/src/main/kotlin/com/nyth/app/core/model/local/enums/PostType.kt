package com.nyth.app.core.model.local.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PostType(val type: String, val tag : String) : Parcelable {
    Post(type = "Post", tag = "Post"),
    ModeratorPost(type = "ModeratorPost", tag = "ModeratorPost");
}

fun getPostTypeByTag(tag: String): PostType? {
    return PostType.values().firstOrNull { it.tag == tag }
}