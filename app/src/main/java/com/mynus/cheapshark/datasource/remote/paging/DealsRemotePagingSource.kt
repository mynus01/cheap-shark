package com.mynus.cheapshark.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mynus.cheapshark.datasource.remote.mapper.DealDTOMapper
import com.mynus.cheapshark.datasource.remote.service.CheapSharkAPIService
import com.mynus.domain.model.Deal
import com.mynus.domain.service.DealLocalService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DealsRemotePagingSource(
    private val service: CheapSharkAPIService,
    private val dealLocalService: DealLocalService
) : PagingSource<Int, Deal>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deal> {
        return try {
            val position = params.key ?: 0

            val response = service.get(position).map { dto ->
                DealDTOMapper.fromDTO(dto)
            }

            CoroutineScope(Dispatchers.IO).launch {
                dealLocalService.insertDeals(response)
            }

            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        }
        catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Deal>): Int? = null
}