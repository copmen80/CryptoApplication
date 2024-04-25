package com.crypto.application.app.di

import com.crypto.application.app.constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @CryptoRetrofit
    @Provides
    fun provideCryptoRetrofit(
        okHttpClient: OkHttpClient,
        @Named("JsonConverterFactory")
        jsonConverterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CryptoRetrofit

