package com.crypto.application.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.application.dashboard.domain.RefreshCurrentCurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val refreshCurrentCurrencyUseCase: RefreshCurrentCurrencyUseCase,
) : ViewModel() {

    fun refreshCurrency() {
        viewModelScope.launch {
            refreshCurrentCurrencyUseCase.invoke()
        }
    }

}