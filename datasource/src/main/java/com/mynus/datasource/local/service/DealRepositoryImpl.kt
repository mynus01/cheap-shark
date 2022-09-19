package com.mynus.datasource.local.service

import com.mynus.datasource.local.dao.DealDao
import com.mynus.datasource.local.mapper.DealEntityMapper
import com.mynus.domain.model.Deal
import com.mynus.domain.repository.DealRepository
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(
    private val dao: DealDao
) : DealRepository {
    override fun insertDeal(deal: Deal) {
        dao.insertDeal(DealEntityMapper.toEntity(deal))
    }

    override fun insertDeals(deals: List<Deal>) {
        dao.insertDeals(deals.map { DealEntityMapper.toEntity(it) })
    }

    override fun getDeal(gameID: String): Deal {
        return DealEntityMapper.fromEntity(dao.getDeal(gameID))
    }

    override fun getDeals(offset: Int): List<Deal> {
        return dao.getDeals(offset).map { entity ->
            DealEntityMapper.fromEntity(entity)
        }
    }

    override fun deleteDeal(deal: Deal) {
        dao.deleteDeal(DealEntityMapper.toEntity(deal))
    }
}