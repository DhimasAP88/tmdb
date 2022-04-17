package com.app.movie.data.repository

import com.app.movie.data.api.ApiHelper
import javax.inject.Inject


class FilmRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getMovieDetail(movieId :String) = apiHelper.getMovieDetail(movieId)
    suspend fun getMovieReview(movieId: String) = apiHelper.getReview(movieId)
    suspend fun getListMovie(listId: String) = apiHelper.getListMovie(listId)
}