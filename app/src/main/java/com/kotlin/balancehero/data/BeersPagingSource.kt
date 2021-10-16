package com.kotlin.balancehero.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kotlin.balancehero.http.ApiInterface

class BeersPagingSource(private val apiInterface: ApiInterface) : PagingSource<Int, Beers>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beers> {
        return try {
            val nextPage = params.key ?: 1
            val response = apiInterface.getList(nextPage)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beers>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}