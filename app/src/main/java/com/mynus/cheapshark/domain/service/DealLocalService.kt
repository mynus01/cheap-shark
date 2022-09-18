package com.mynus.cheapshark.domain.service

import com.mynus.cheapshark.domain.model.Deal

interface DealLocalService {
    fun insertDeal(deal: Deal)
    fun insertDeals(deals: List<Deal>)
    fun getDeal(gameID: String): Deal
    fun getDeals(offset: Int): List<Deal>
    fun deleteDeal(deal: Deal)
}