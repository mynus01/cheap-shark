package com.mynus.cheapshark.datasource.local.paging

import androidx.paging.*
import com.mynus.cheapshark.datasource.local.dao.DealDao
import com.mynus.cheapshark.datasource.local.mapper.DealEntityMapper
import com.mynus.cheapshark.domain.model.Deal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DealsLocalPagingSource(
    private val dao: DealDao
) : PagingSource<Int, Deal>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deal> = withContext(Dispatchers.IO) {
        val position = params.key ?: 0

        val result = dao.getDeals(position).map { entity ->
            DealEntityMapper.fromEntity(entity)
        }

        return@withContext LoadResult.Page(
            data = result,
            prevKey = if (position == 0) null else position,
            nextKey = if (result.isEmpty()) null else position + 60
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Deal>): Int? = null
}
