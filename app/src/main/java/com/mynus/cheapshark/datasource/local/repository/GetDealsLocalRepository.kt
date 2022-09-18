package com.mynus.cheapshark.datasource.local.repository

import androidx.paging.PagingData
import com.mynus.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface GetDealsLocalRepository {
    suspend fun get(): Flow<PagingData<Deal>>
}