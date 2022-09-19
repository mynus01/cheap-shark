package com.mynus.datasource.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mynus.datasource.remote.paging.DealsRemotePagingSource
import com.mynus.datasource.remote.service.CheapSharkAPIService
import com.mynus.domain.model.Deal
import com.mynus.domain.repository.DealRepository
import com.mynus.domain.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DealsRemoteRepositoryImpl @Inject constructor(
    private val cheapSharkService: CheapSharkAPIService,
    private val dealRepository: DealRepository
) : GetDealsRemoteRepository {
    override suspend fun get(): Flow<PagingData<Deal>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MagicNumbers.PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { DealsRemotePagingSource(cheapSharkService, dealRepository) }
        ).flow
    }
}