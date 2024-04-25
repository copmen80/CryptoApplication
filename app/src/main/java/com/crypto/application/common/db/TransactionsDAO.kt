package com.crypto.application.common.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TransactionsDAO {
    @Insert(entity = TransactionsEntity::class)
    suspend fun insertNewTransaction(transaction: TransactionsEntity)

    @Query("SELECT * FROM transactions ORDER BY time DESC")
    fun getAllTransaction(): PagingSource<Int, TransactionsEntity>

}