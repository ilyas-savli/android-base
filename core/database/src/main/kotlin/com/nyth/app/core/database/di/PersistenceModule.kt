package com.nyth.app.core.database.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.nyth.app.core.database.db.AppDao
import com.nyth.app.core.database.db.AppDatabase
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    // region Room
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "App.db")
        .fallbackToDestructiveMigration(false).build()

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase): AppDao = appDatabase.appDao()
    // endregion

    // region SharedPref
    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferenceManager =
        SharedPreferenceManager(context = context)
    // endregion
}
