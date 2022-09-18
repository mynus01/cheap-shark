package com.mynus.cheapshark.domain.usecase

interface ConnectionChecker {
    fun hasConnection(): Boolean
}