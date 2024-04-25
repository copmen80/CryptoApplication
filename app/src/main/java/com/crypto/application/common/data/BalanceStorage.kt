package com.crypto.application.common.data

import com.crypto.application.app.prefs.PreferencesStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BalanceStorage @Inject constructor(private val prefs: PreferencesStorage) {
    private val _value = MutableStateFlow(prefs.balance)
    val flow = _value.asStateFlow()
    suspend fun emit(value: Float) {
        prefs.balance = value
        _value.emit(value)
    }
}
