package com.crypto.application.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.crypto.application.common.data.BalanceStorage
import com.crypto.application.common.data.CurrencyStorage
import com.crypto.application.dashboard.domain.DepositUseCase
import com.crypto.application.dashboard.domain.GetTransactionsUseCase
import com.crypto.application.dashboard.domain.RefreshCurrentCurrencyUseCase
import com.crypto.application.dashboard.domain.mapper.TransactionUiModelMapper
import com.crypto.application.newtransaction.domain.mapper.TransactionEntityMapper
import com.crypto.application.newtransaction.ui.model.TransactionDateUiModel
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val refreshCurrentCurrencyUseCase: RefreshCurrentCurrencyUseCase,
    private val depositUseCase: DepositUseCase,
    private val transactionEntityMapper: TransactionEntityMapper,
    private val balanceStorage: BalanceStorage,
    private val currencyStorage: CurrencyStorage,
    private val transactionUiModelMapper: TransactionUiModelMapper
) : ViewModel() {

    val transactionsData = getTransactionsUseCase.invoke().map { pagingData ->
        pagingData
            .map { transactionUiModelMapper.map(it) }
            .insertSeparators { transactionsA, transactionsB ->
                val dayA =
                    (transactionsA as? TransactionUiModel)?.time?.get(Calendar.DAY_OF_YEAR) ?: 0
                val dayB =
                    (transactionsB as? TransactionUiModel)?.time?.get(Calendar.DAY_OF_YEAR) ?: 0
                if (dayB != 0 && dayA != dayB) {
                    val dateFormat = SimpleDateFormat("EEEE MMMM dd", Locale.getDefault())
                    val formattedDate = (transactionsB as? TransactionUiModel)?.time?.time?.let {
                        dateFormat.format(it)
                    }
                    TransactionDateUiModel(formattedDate)
                } else null
            }
    }.cachedIn(viewModelScope)

    val balance = balanceStorage.flow
    val currency = currencyStorage.flow

    init {
        viewModelScope.launch {
            refreshCurrentCurrencyUseCase.invoke()
        }
    }

    fun addNewDeposit(transaction: TransactionUiModel) {
        viewModelScope.launch {
            balanceStorage.emit(balanceStorage.flow.value + transaction.amount)
            depositUseCase.addNewTransaction(transactionEntityMapper.map(transaction))
        }
    }
}
