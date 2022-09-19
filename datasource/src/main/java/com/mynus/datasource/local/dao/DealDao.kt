package com.mynus.datasource.local.dao

import androidx.room.*
import com.mynus.datasource.local.entity.DealEntity
import com.mynus.domain.util.Constants

@Dao
interface DealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeal(deal: DealEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeals(deals: List<DealEntity>)

    @Query("SELECT * FROM DealEntity WHERE dealID = :gameID ORDER BY title ASC")
    fun getDeal(gameID: String): DealEntity

    @Query("SELECT * FROM DealEntity ORDER BY title ASC LIMIT ${Constants.MagicNumbers.PAGE_SIZE} OFFSET :offset")
    fun getDeals(offset: Int): List<DealEntity>

    @Delete
    fun deleteDeal(deal: DealEntity)
}