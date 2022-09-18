package com.mynus.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mynus.datasource.local.dao.DealDao
import com.mynus.datasource.local.entity.DealEntity

@Database(
    entities = [
        DealEntity::class,
    ], exportSchema = true,
    version = 1
)
abstract class CheapSharkDatabase : RoomDatabase() {
    abstract fun dealDao(): DealDao
}