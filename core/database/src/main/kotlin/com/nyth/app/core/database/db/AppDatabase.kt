package com.nyth.app.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 8,
    entities = [ExampleModel::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
