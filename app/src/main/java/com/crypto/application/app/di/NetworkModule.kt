package com.crypto.application.app.di

import com.crypto.application.dashboard.data.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideCurrencyService(@CryptoRetrofit retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)
}