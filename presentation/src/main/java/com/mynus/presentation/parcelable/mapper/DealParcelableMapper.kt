package com.mynus.presentation.parcelable.mapper

import com.mynus.domain.model.Deal
import com.mynus.presentation.parcelable.model.DealParcelable

object DealParcelableMapper {
    fun fromParcelable(parcelable: DealParcelable): Deal {
        parcelable.apply {
            return Deal(
                dealID = dealID,
                title = title,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale,
                thumb = thumb,
                steamAppID = steamAppID,
                metacriticLink = metacriticLink
            )
        }
    }

    fun toParcelable(deal: Deal):  DealParcelable{
        deal.apply {
            return DealParcelable(
                dealID = dealID,
                title = title,
                salePrice = salePrice,
                normalPrice = normalPrice,
                isOnSale = isOnSale,
                thumb = thumb,
                steamAppID = steamAppID,
                metacriticLink = metacriticLink
            )
        }
    }
}