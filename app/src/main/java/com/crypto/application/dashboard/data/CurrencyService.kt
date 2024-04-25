package com.crypto.application.dashboard.data

import retrofit2.http.GET

interface CurrencyService {
    @GET("v1/bpi/currentprice.json")
    suspend fun getCurrency(): BitcoinResponse
}