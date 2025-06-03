package com.nyth.app.core.database.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import com.nyth.app.core.database.EncryptedDataStoreManager
import com.nyth.app.core.database.db.AppDao
import com.nyth.app.core.database.db.AppDatabase
import com.nyth.app.core.database.factory.createEncrypted
import com.nyth.app.core.database.internal.ResourceManager
import com.nyth.app.core.database.sharedpref.SharedPreferenceManager
import com.nyth.app.core.model.utils.AppConstants.Companion.DATASTORE_FILE
import com.nyth.app.core.model.utils.AppConstants.Companion.ENCRYPTION_TYPE
import com.nyth.app.core.model.utils.AppConstants.Companion.KEYSET_NAME
import com.nyth.app.core.model.utils.AppConstants.Companion.MASTER_KEY_URI
import com.nyth.app.core.model.utils.AppConstants.Companion.PREFERENCE_FILE
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

    // region Encrypted DataStore
    @Singleton
    @Provides
    fun provideAead(application: Application): Aead {
        AeadConfig.register()

        return AndroidKeysetManager.Builder()
            .withSharedPref(application, KEYSET_NAME, PREFERENCE_FILE)
            .withKeyTemplate(KeyTemplates.get(ENCRYPTION_TYPE)).withMasterKeyUri(MASTER_KEY_URI)
            .build().keysetHandle.getPrimitive(Aead::class.java)
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.createEncrypted(corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }),
            produceFile = {
                EncryptedFile.Builder(
                    appContext.preferencesDataStoreFile(DATASTORE_FILE),
                    appContext,
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
                ).build()
            })

    @Singleton
    @Provides
    fun provideEncryptedDataStoreManager(
        dataStore: DataStore<Preferences>,
        sharedPref: SharedPreferenceManager
    ): EncryptedDataStoreManager =
        EncryptedDataStoreManager(dataStore = dataStore, sharedPref = sharedPref)

    // endregion

    // region Room
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "App.db")
        .fallbackToDestructiveMigration().build()

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

    // region ResourceManager
    @Provides
    @Singleton
    fun provideResourceManager(@ApplicationContext context: Context) = ResourceManager(context)
    // endregion
}
