package com.crypto.application.dashboard.domain

import com.crypto.application.common.data.TransactionsRepository
import com.crypto.application.common.db.TransactionsEntity
import javax.inject.Inject

class DepositUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {

    suspend fun addNewTransaction(transaction: TransactionsEntity) =
        transactionsRepository.insertNewTransaction(transaction)

}