package com.mynus.cheapshark.di

import android.content.Context
import com.mynus.datasource.mediator.DealsMediatorImpl
import com.mynus.datasource.remote.util.ConnectionCheckerImpl
import com.mynus.datasource.mediator.DealsMediator
import com.mynus.datasource.remote.repository.GetDealsRemoteRepository
import com.mynus.datasource.local.repository.GetDealsLocalRepository
import com.mynus.domain.util.ConnectionChecker
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
        return com.mynus.datasource.remote.util.ConnectionCheckerImpl(ctx)
    }

    @Provides
    fun dealsMediatorProvider(
        remoteRepository: com.mynus.datasource.remote.repository.GetDealsRemoteRepository,
        localRepository: com.mynus.datasource.local.repository.GetDealsLocalRepository,
        connectionChecker: ConnectionChecker
    ): com.mynus.datasource.mediator.DealsMediator {
        return com.mynus.datasource.mediator.DealsMediatorImpl(remoteRepository, localRepository, connectionChecker)
    }
}