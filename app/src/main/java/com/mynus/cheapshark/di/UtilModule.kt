package com.mynus.cheapshark.di

import android.content.Context
import com.mynus.cheapshark.datasource.mediator.DealsMediatorImpl
import com.mynus.cheapshark.datasource.remote.util.ConnectionCheckerImpl
import com.mynus.cheapshark.domain.mediator.DealsMediator
import com.mynus.cheapshark.domain.repository.GetDealsRemoteRepository
import com.mynus.cheapshark.domain.repository.GetDealsLocalRepository
import com.mynus.cheapshark.domain.usecase.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {
    @Provides
    fun connectionCheckerProvider(
        @ApplicationContext ctx: Context
    ): ConnectionChecker {
        return ConnectionCheckerImpl(ctx)
    }

    @Provides
    fun dealsMediatorProvider(
        remoteRepository: GetDealsRemoteRepository,
        localRepository: GetDealsLocalRepository,
        connectionChecker: ConnectionChecker
    ): DealsMediator {
        return DealsMediatorImpl(remoteRepository, localRepository, connectionChecker)
    }
}