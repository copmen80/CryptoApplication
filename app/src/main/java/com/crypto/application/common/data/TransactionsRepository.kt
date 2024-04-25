package com.crypto.application.common.data

import androidx.paging.PagingSource
import com.crypto.application.common.db.TransactionsDAO
import com.crypto.application.common.db.TransactionsEntity
import javax.inject.Inject

class TransactionsRepository @Inject constructor(
    private val transactionsDAO: TransactionsDAO
) {
    suspend fun insertNewTransaction(transaction: TransactionsEntity) =
        transactionsDAO.insertNewTransaction(transaction)

     fun getAllTransaction(): PagingSource<Int, TransactionsEntity> =
        transactionsDAO.getAllTransaction()

}
