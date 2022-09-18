package com.mynus.cheapshark.datasource.remote.mapper

import com.mynus.cheapshark.datasource.remote.dto.DealDTO
import com.mynus.cheapshark.domain.model.Deal

object DealDTOMapper {
    fun fromDTO(dto: DealDTO): Deal {
        dto.apply {
            return Deal(
                title = title,
                dealID = dealID,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale == "1",
                thumb = thumb
            )
        }
    }
}