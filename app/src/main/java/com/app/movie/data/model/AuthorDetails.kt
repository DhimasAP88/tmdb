package com.app.movie.data.model

import com.squareup.moshi.Json

data class AuthorDetails(
    @Json(name = "username") val username: String,
    @Json(name = "avatar_path") val avatar_path: String
)