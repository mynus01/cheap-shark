package com.mynus.domain.service

import com.mynus.domain.model.Deal

interface DealService {
    fun get(pageNumber: Int, pageSize: Int): List<Deal>
}