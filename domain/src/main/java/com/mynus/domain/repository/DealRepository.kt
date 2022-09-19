package com.mynus.domain.repository

import com.mynus.domain.model.Deal

interface DealRepository {
    fun insertDeal(deal: Deal)
    fun insertDeals(deals: List<Deal>)
    fun getDeal(gameID: String): Deal
    fun getDeals(offset: Int): List<Deal>
    fun deleteDeal(deal: Deal)
}