package com.app.movie.data.model

import com.squareup.moshi.Json

data class ItemsFilm(
    @Json(name = "items") val items: List<Film>,
    @Json(name = "description") val description: String
)