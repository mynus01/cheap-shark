package com.mynus.cheapshark.di

import com.mynus.cheapshark.datasource.remote.repository.DealsRemoteRepositoryImpl
import com.mynus.cheapshark.domain.repository.GetDealsRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteModule {
    @Binds
    fun remoteRepositoryProvider(impl: DealsRemoteRepositoryImpl): GetDealsRemoteRepository
}