package com.mynus.datasource.local.paging

import androidx.paging.*
import com.mynus.datasource.local.dao.DealDao
import com.mynus.datasource.local.mapper.DealEntityMapper
import com.mynus.domain.model.Deal
import com.mynus.core.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DealsLocalPagingSource(
    private val dao: DealDao
) : PagingSource<Int, Deal>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Deal> = withContext(Dispatchers.IO) {
        try {
            val position = params.key ?: 0

            val result = dao.getDeals(position).map { entity ->
                DealEntityMapper.fromEntity(entity)
            }

        return@withContext LoadResult.Page(
            data = result,
            prevKey = if (position == 0) null else position,
            nextKey = if (result.isEmpty()) null else position + Constants.MagicNumbers.PAGE_SIZE
        )
        } catch (e: IOException) {
            return@withContext LoadResult.Error(e)
        } catch (e: HttpException) {
            return@withContext LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Deal>): Int? = null
}
