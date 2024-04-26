package com.crypto.application.newtransaction.ui.model

import java.util.Calendar


enum class TransactionType {
    DEPOSIT,
    WITHDRAWAL,
}

sealed class DashboardItemUiModel
data class TransactionDateUiModel(val time: Calendar?) : DashboardItemUiModel()
data class TransactionUiModel(
    val time: Calendar,
    val amount: Float,
    val category: String? = null,
    val type: TransactionType,
) : DashboardItemUiModel()