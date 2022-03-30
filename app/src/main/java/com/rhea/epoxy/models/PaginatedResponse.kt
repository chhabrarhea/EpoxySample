package com.rhea.epoxy.models

import com.squareup.moshi.Json

data class PaginatedResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<Movie>,
    @Json(name = "total_results")
    val total_results: Int,
    @Json(name = "total_pages")
    val total_pages: Int
)