package com.app.movie.data.model

import com.squareup.moshi.Json

data class Result(
    @Json(name = "results") val results: List<Review>
)