package com.mynus.domain.model

data class Deal(
    val dealID: String,
    val title: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: Boolean,
    val thumb: String,
    val steamAppID: String?,
    val metacriticLink: String?
)