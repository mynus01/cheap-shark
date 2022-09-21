package com.mynus.presentation.parcelable.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DealParcelable(
    val dealID: String,
    val title: String,
    val salePrice: String,
    val normalPrice: String,
    val isOnSale: Boolean,
    val thumb: String,
    val steamAppID: String?,
    val metacriticLink: String?
): Parcelable