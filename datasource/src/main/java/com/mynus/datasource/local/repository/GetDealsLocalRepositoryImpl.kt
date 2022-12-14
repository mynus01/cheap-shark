package com.mynus.datasource.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mynus.datasource.local.dao.DealDao
import com.mynus.datasource.local.paging.DealsLocalPagingSource
import com.mynus.domain.model.Deal
import com.mynus.core.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDealsLocalRepositoryImpl @Inject constructor(
    private val dao: DealDao
): GetDealsLocalRepository {
    override suspend fun get(): Flow<PagingData<Deal>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MagicValues.PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { DealsLocalPagingSource(dao) }
        ).flow
    }
}