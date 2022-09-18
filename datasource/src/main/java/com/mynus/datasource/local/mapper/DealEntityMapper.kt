package com.mynus.datasource.local.mapper

import com.mynus.datasource.local.entity.DealEntity
import com.mynus.domain.model.Deal

object DealEntityMapper {
    fun fromEntity(entity: DealEntity): Deal {
        entity.apply {
            return Deal(
                dealID = dealID,
                title = title,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale,
                thumb = thumb
            )
        }
    }

    fun toEntity(deal: Deal): DealEntity {
        deal.apply {
            return DealEntity(
                dealID = dealID,
                title = title,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale,
                thumb = thumb
            )
        }
    }
}