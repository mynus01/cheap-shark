package com.mynus.datasource.mediator

import androidx.paging.PagingData
import com.mynus.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface DealsMediator {
   suspend fun getDeals(): Flow<PagingData<Deal>>
}