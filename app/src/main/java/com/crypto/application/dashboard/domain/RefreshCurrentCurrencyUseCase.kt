package com.crypto.application.dashboard.domain

import com.crypto.application.app.prefs.PreferencesStorage
import com.crypto.application.common.data.CurrencyStorage
import com.crypto.application.dashboard.data.CurrencyRepository
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RefreshCurrentCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val preferencesStorage: PreferencesStorage,
    private val currencyStorage: CurrencyStorage,
) {
    suspend fun invoke() {
        if (currencyStorage.flow.value.isEmpty() || shouldUpdateBitcoinRate()) {
            updateBitcoinRate()
        }
    }

    private fun shouldUpdateBitcoinRate(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis
        return hoursBetween(preferencesStorage.lastUpdateTime, currentTime) >= 1
    }

    private suspend fun updateBitcoinRate() {
        val newCurrencyRate = currencyRepository.getCurrentCurrency().bpi.USD.rate
        currencyStorage.emit(newCurrencyRate)
        preferencesStorage.lastUpdateTime = Calendar.getInstance().timeInMillis
    }

    private fun hoursBetween(startDate: Long, endDate: Long): Long {
        val diff = endDate - startDate
        return diff / TimeUnit.HOURS.toMillis(1)
    }
}
