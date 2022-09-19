package com.mynus.datasource.remote.service

import com.mynus.core.util.Constants.Endpoints
import com.mynus.datasource.remote.dto.DealDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CheapSharkAPIService {
    @GET(Endpoints.DEALS)
    suspend fun get(
        @Query("pageNumber")
        pageNumber: Int,
        @Query("sortBy")
        sortBy: String = "Title",
        @Query("metacritic")
        metacriticScore: Int = 75,
        @Query("onSale")
        onSale: Boolean = true
    ): List<DealDTO>
}