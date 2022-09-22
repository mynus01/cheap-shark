package com.mynus.datasource.base

import com.mynus.domain.model.Deal
import java.util.concurrent.atomic.AtomicInteger

object DealFactory {
    private val counter = AtomicInteger(0)

    fun createDeal(): Deal {
        val id = counter.getAndIncrement()
        return Deal(
            dealID = id.toString(),
            title = "title",
            salePrice = "1.0",
            normalPrice = id.toFloat().toString(),
            isOnSale = id > 1,
            thumb = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png",
            steamAppID = null,
            metacriticLink = null
        )
    }
}