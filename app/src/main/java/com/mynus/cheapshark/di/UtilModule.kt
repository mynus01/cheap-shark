package com.mynus.cheapshark.di

import android.content.Context
import com.mynus.datasource.mediator.DealsMediatorImpl
import com.mynus.datasource.remote.util.ConnectionCheckerImpl
import com.mynus.datasource.mediator.DealsMediator
import com.mynus.datasource.remote.repository.GetDealsRemoteRepository
import com.mynus.datasource.local.repository.GetDealsLocalRepository
import com.mynus.core.util.ConnectionChecker
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