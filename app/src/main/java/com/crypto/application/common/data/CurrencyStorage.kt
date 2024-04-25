package com.crypto.application.common.data

import com.crypto.application.app.prefs.PreferencesStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyStorage @Inject constructor(private val prefs: PreferencesStorage) {
    private val _value = MutableStateFlow(prefs.currency)
    val flow = _value.asStateFlow()
    suspend fun emit(value: String) {
        prefs.currency = value
        _value.emit(value)
    }
}