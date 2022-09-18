package com.mynus.cheapshark.di

import com.mynus.cheapshark.datasource.local.repository.GetDealsLocalRepositoryImpl
import com.mynus.cheapshark.datasource.local.service.DealLocalServiceImpl
import com.mynus.cheapshark.datasource.local.repository.GetDealsLocalRepository
import com.mynus.domain.service.DealLocalService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalModule {
    @Binds
    fun localServiceProvider(impl: DealLocalServiceImpl): DealLocalService

    @Binds
    fun getDealsRepositoryProvider(impl: GetDealsLocalRepositoryImpl): GetDealsLocalRepository
}