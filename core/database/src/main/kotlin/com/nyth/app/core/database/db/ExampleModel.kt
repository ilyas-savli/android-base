package com.nyth.app.core.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("example")
data class ExampleModel(
    @PrimaryKey
    val str: String = ""
)
