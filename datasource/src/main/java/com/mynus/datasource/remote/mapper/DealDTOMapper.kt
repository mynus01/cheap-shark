package com.mynus.datasource.remote.mapper

import com.mynus.datasource.remote.dto.DealDTO
import com.mynus.domain.model.Deal

object DealDTOMapper {
    fun fromDTO(dto: DealDTO): Deal {
        dto.apply {
            return Deal(
                title = title,
                dealID = dealID,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale == "1",
                thumb = thumb,
                steamAppID = steamAppID,
                metacriticLink = metacriticLink
            )
        }
    }
}