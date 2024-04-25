package com.crypto.application.newtransaction.domain

import com.crypto.application.common.data.TransactionsRepository
import com.crypto.application.common.db.TransactionsEntity
import javax.inject.Inject

class AddTransactionUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {

    suspend fun addNewTransaction(transaction: TransactionsEntity) =
        transactionsRepository.insertNewTransaction(transaction)

}