package com.mynus.datasource.mediator

import androidx.paging.PagingData
import com.mynus.domain.model.Deal
import com.mynus.datasource.remote.repository.GetDealsRemoteRepository
import com.mynus.datasource.local.repository.GetDealsLocalRepository
import com.mynus.domain.util.ConnectionChecker
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