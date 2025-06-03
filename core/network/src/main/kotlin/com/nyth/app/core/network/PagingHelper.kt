package com.nyth.app.core.network

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingHelper<T : Any>(
    private val onLoad: suspend (pageIndex: Int, pageSize: Int) -> LoadResult<Int, T>,
    private val pageSize: Int = 10
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> =
        try {
            onLoad.invoke(params.key ?: 1, pageSize)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}