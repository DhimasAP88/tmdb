package com.app.movie.data.model

import com.squareup.moshi.Json

data class Film(
    @Json(name = "title") val title: String,
    @Json(name = "id") val id: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val release_date: String,
    @Json(name = "vote_average") val vote_average: String,
    @Json(name = "backdrop_path") val backdrop_path: String,
    @Json(name = "vote_count") val vote_count: String
)