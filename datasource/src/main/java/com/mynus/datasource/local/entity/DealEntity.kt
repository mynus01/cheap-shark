package com.mynus.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DealEntity(
    @PrimaryKey
    val dealID: String,
    val title: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: Boolean,
    val thumb: String,
    val steamAppID: String?,
    val metacriticLink: String?
)