package com.mynus.datasource.remote.service

import com.mynus.core.util.Constants
import com.mynus.core.util.Constants.Endpoints
import com.mynus.datasource.remote.dto.DealDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CheapSharkAPIService {
    @GET(Endpoints.DEALS)
    suspend fun get(
        @Query("pageNumber")
        pageNumber: Int,
        @Query("pageSize")
        pageSize: Int = Constants.MagicValues.PAGE_SIZE,
        @Query("sortBy")
        sortBy: String = Constants.MagicValues.SORT_BY,
        @Query("metacritic")
        metacriticScore: Int = Constants.MagicValues.MIN_METACRITIC_RATING,
    ): List<DealDTO>
}