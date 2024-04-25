package com.crypto.application.newtransaction.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.application.common.data.BalanceStorage
import com.crypto.application.newtransaction.domain.AddTransactionUseCase
import com.crypto.application.newtransaction.domain.mapper.TransactionEntityMapper
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class NewTransactionAction {
    object Success : NewTransactionAction()
    object NotEnoughBalance : NewTransactionAction()
}

@HiltViewModel
class NewTransactionViewModel @Inject constructor(
    private val addTransactionUseCase: AddTransactionUseCase,
    private val transactionEntityMapper: TransactionEntityMapper,
    private val balanceStorage: BalanceStorage,
) : ViewModel() {

    private val eventChannel = Channel<NewTransactionAction>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
    fun addNewTransaction(transaction: TransactionUiModel) {
        viewModelScope.launch {
            if (balanceStorage.flow.value >= transaction.amount) {
                balanceStorage.emit(balanceStorage.flow.value - transaction.amount)
                addTransactionUseCase.addNewTransaction(transactionEntityMapper.map(transaction))
                eventChannel.send(NewTransactionAction.Success)
            } else {
                eventChannel.send(NewTransactionAction.NotEnoughBalance)
            }
        }
    }
}