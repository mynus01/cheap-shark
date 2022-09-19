package com.mynus.domain.presenter

import com.mynus.domain.model.Deal
import kotlinx.coroutines.flow.Flow

interface DealPresenter {
    fun onReceive(deals: Flow<List<Deal>>)
}