package com.app.movie.data.model

import com.squareup.moshi.Json

data class Review(
    @Json(name = "author") val author : String,
    @Json(name = "author_details") val author_details : AuthorDetails,
    @Json(name = "content") val content : String
)