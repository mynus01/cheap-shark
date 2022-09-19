package com.mynus.cheapshark.di

import com.mynus.domain.repository.DealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalModule {
    @Binds
    fun localServiceProvider(impl: com.mynus.datasource.local.service.DealRepositoryImpl): DealRepository

    @Binds
    fun getDealsRepositoryProvider(impl: com.mynus.datasource.local.repository.GetDealsLocalRepositoryImpl): com.mynus.datasource.local.repository.GetDealsLocalRepository
}