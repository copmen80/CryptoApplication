package com.crypto.application.app.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.crypto.application.app.constants.PREF_APP_PREFS
import com.crypto.application.app.prefs.PreferencesStorage
import com.crypto.application.common.db.AppDatabase
import com.crypto.application.common.db.TransactionsDAO
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideDB(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java,
            "TransactionsDB"
        ).build()
    }

    @Provides
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionsDAO {
        return appDatabase.getTransactionsDao()
    }

    @Singleton
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Named("JsonConverterFactory")
    @Provides
    fun provideJsonConverterFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun provideResources(app: Application): Resources = app.resources

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): PreferencesStorage {
        return PreferencesStorage(
            context.getSharedPreferences(
                PREF_APP_PREFS,
                Application.MODE_PRIVATE
            )
        )
    }

}