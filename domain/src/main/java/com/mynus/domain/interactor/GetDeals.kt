package com.mynus.domain.interactor

import com.mynus.domain.presenter.DealPresenter
import com.mynus.domain.repository.DealRepository
import com.mynus.domain.service.DealService
import com.mynus.domain.util.ConnectionChecker
import com.mynus.domain.util.Constants
import kotlinx.coroutines.flow.flowOf

class GetDeals(
    private val service: DealService,
    private val repository: DealRepository,
    private val presenter: DealPresenter,
    private val connectionChecker: ConnectionChecker
) {
    fun execute(pageNumber: Int) {
        val result = if (connectionChecker.hasConnection()) {
            val serviceDeals = service.get(pageNumber, Constants.MagicNumbers.PAGE_SIZE)
            repository.insertDeals(serviceDeals)
            serviceDeals
        } else {
            repository.getDeals(pageNumber * Constants.MagicNumbers.PAGE_SIZE)
        }

        presenter.onReceive(flowOf(result))
    }
}