package com.mynus.datasource.remote.repository

import androidx.paging.PagingData
import com.mynus.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface GetDealsRemoteRepository {
    suspend fun get(): Flow<PagingData<Deal>>
}