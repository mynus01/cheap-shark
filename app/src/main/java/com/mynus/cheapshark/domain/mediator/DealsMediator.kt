package com.mynus.cheapshark.domain.mediator

import androidx.paging.PagingData
import com.mynus.cheapshark.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface DealsMediator {
   suspend fun getDeals(): Flow<PagingData<Deal>>
}