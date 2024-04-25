package com.crypto.application.dashboard.domain.mapper

import com.crypto.application.common.db.TransactionsEntity
import com.crypto.application.newtransaction.ui.model.DashboardItemUiModel
import com.crypto.application.newtransaction.ui.model.TransactionUiModel
import javax.inject.Inject

class TransactionUiModelMapper @Inject constructor() {

    fun map(transactionsEntity: TransactionsEntity): DashboardItemUiModel {
        return TransactionUiModel(
            time = transactionsEntity.time,
            amount = transactionsEntity.amount,
            category = transactionsEntity.category,
            type = transactionsEntity.transactionType
        )
    }
}