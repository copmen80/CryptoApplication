package com.crypto.application.dashboard.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.crypto.application.common.data.TransactionsRepository
import com.crypto.application.common.db.TransactionsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
) {

    fun invoke(): Flow<PagingData<TransactionsEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            prefetchDistance = 20
        )
    ) { transactionsRepository.getAllTransaction() }.flow

}