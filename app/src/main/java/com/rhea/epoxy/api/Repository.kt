package com.rhea.epoxy.api

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.haroldadmin.cnradapter.NetworkResponse
import com.rhea.epoxy.di.IODispatcher
import com.rhea.epoxy.models.Movie
import com.rhea.epoxy.paged.MoviePagingSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val slashService: Service,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) {
   suspend fun getSimpleMovieList(): Resource<List<Movie>>{
       val response = withContext(dispatcher){ slashService.getPopularMovies()}
       return when(response){
           is NetworkResponse.Success -> Resource.success(response.body.results)
           is NetworkResponse.NetworkError -> Resource.failed(response.error)
           is NetworkResponse.ServerError -> Resource.failed(Exception(response.error))
           is NetworkResponse.UnknownError -> Resource.failed(Exception(response.error))
       }
   }

    fun getPagedMovieList() = Pager(
       config =  PagingConfig(10, 20, initialLoadSize = 10),
       pagingSourceFactory =  { MoviePagingSource(dispatcher, slashService) }
    ).flow
}