package com.crypto.application.common.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crypto.application.newtransaction.ui.model.TransactionType
import java.util.Calendar

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey val time: Calendar,
    val amount: Int,
    val category: String? = null,
    val transactionType: TransactionType
)
