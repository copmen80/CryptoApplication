package com.crypto.application.newtransaction.domain.mapper

import com.crypto.application.common.db.TransactionsEntity
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import javax.inject.Inject

class TransactionEntityMapper @Inject constructor() {

    fun map(transactionsUiModel: TransactionUiModel): TransactionsEntity {
        return TransactionsEntity(
            time = transactionsUiModel.time,
            amount = transactionsUiModel.amount,
            category = transactionsUiModel.category ,
            transactionType = transactionsUiModel.type
        )
    }
}