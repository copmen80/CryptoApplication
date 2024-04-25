package com.crypto.application.dashboard.domain

import com.crypto.application.app.prefs.PreferencesStorage
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val preferencesStorage: PreferencesStorage
) {
     fun invoke() = preferencesStorage.balance
}