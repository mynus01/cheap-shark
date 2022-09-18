package com.mynus.datasource.remote.dto

data class DealDTO(
    val dealID: String,
    val title: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: String,
    val thumb: String
)