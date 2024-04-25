package com.crypto.application.dashboard.data

import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyService: CurrencyService
) {
    suspend fun getCurrentCurrency() = currencyService.getCurrency()
}