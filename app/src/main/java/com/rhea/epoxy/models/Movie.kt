package com.rhea.epoxy.models

import com.squareup.moshi.Json

data class Movie (
    @Json(name = "id")
    val id: Int,
    @Json(name = "poster_path")
    val poster_path: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String
)