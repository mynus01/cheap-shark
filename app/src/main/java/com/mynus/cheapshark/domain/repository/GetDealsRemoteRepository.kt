package com.mynus.cheapshark.domain.repository

import androidx.paging.PagingData
import com.mynus.cheapshark.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface GetDealsRemoteRepository {
    suspend fun get(): Flow<PagingData<Deal>>
}