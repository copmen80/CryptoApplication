package com.crypto.application.dashboard.domain

import android.util.Log
import com.crypto.application.app.prefs.PreferencesStorage
import com.crypto.application.common.data.CurrencyStorage
import com.crypto.application.dashboard.data.CurrencyRepository
import java.util.Calendar
import javax.inject.Inject

class RefreshCurrentCurrencyUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val preferencesStorage: PreferencesStorage,
    private val currencyStorage: CurrencyStorage,
) {
    suspend fun invoke() {
        if (currencyStorage.flow.value.isEmpty() || shouldUpdateBitcoinRate()) {
            Log.d("CURRENCY", "1")
            updateBitcoinRate()
        }
    }

    private fun shouldUpdateBitcoinRate(): Boolean {
        Log.d("CURRENCY", "3")
        val currentTime = Calendar.getInstance().timeInMillis
        return hoursBetween(preferencesStorage.lastUpdateTime, currentTime) >= 1
    }

    private suspend fun updateBitcoinRate() {
        Log.d("CURRENCY", "4")
        val newCurrencyRate = currencyRepository.getCurrentCurrency().bpi.USD.rate

        currencyStorage.emit(newCurrencyRate)

        preferencesStorage.lastUpdateTime = Calendar.getInstance().timeInMillis
    }

    private fun hoursBetween(startDate: Long, endDate: Long): Long {
        val diff = endDate - startDate
        return diff / (1000 * 60 * 60)
    }
}
