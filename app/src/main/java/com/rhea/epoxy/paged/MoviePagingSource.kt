package com.rhea.epoxy.paged

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import com.rhea.epoxy.api.Service
import com.rhea.epoxy.models.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MoviePagingSource (
   private val dispatcher: CoroutineDispatcher,
    private val service: Service
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>) = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1
        return try {
            val response = withContext(dispatcher) { service.getPagedList(page = pageNumber) }
            if (response is NetworkResponse.Success) {
                val pagedResponse = response.body

                var nextPage: Int? = null
                val totalPages = pagedResponse.total_pages
                val currPage = pagedResponse.page

                if (totalPages > currPage ) nextPage = currPage + 1
                LoadResult.Page<Int, Movie>(
                    pagedResponse.results,
                    if (nextPage == 2) null else pageNumber - 1,
                    nextPage
                )
            } else LoadResult.Error(Exception("Something went wrong"))
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}