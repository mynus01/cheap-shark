package com.mynus.cheapshark.di

import android.content.Context
import androidx.room.Room
import com.mynus.cheapshark.BuildConfig
import com.mynus.datasource.local.dao.DealDao
import com.mynus.datasource.local.database.CheapSharkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun databaseProvider(@ApplicationContext appContext: Context): CheapSharkDatabase {
        return Room.databaseBuilder(
            appContext,
            CheapSharkDatabase::class.java, BuildConfig.DB_NAME
        ).build()
    }

    @Provides
    fun dealDaoProvider(database: CheapSharkDatabase): DealDao {
        return database.dealDao()
    }
}