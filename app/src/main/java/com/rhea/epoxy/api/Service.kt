package com.rhea.epoxy.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.rhea.epoxy.models.PaginatedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = "a334a8b0fb820b10767f9c8c1306143f"
    ): NetworkResponse<PaginatedResponse, Unit>

    @GET("movie/popular")
    suspend fun getPagedList(
        @Query("api_key") key: String = "a334a8b0fb820b10767f9c8c1306143f",
        @Query("page") page: Int
    ): NetworkResponse<PaginatedResponse, Unit>
}