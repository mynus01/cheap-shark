package com.mynus.cheapshark.datasource.mediator

import androidx.paging.PagingData
import com.mynus.cheapshark.domain.mediator.DealsMediator
import com.mynus.cheapshark.domain.model.Deal
import com.mynus.cheapshark.domain.repository.GetDealsRemoteRepository
import com.mynus.cheapshark.domain.repository.GetDealsLocalRepository
import com.mynus.cheapshark.domain.usecase.ConnectionChecker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DealsMediatorImpl @Inject constructor(
    private val remoteRepository: GetDealsRemoteRepository,
    private val localRepository: GetDealsLocalRepository,
    private val connectionChecker: ConnectionChecker
): DealsMediator {
    override suspend fun getDeals(): Flow<PagingData<Deal>> {
        return if (connectionChecker.hasConnection()) {
            remoteRepository.get()
        } else {
            localRepository.get()
        }
    }
}