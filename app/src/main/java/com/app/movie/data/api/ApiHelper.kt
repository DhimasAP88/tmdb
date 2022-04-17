package com.app.movie.data.api

import com.app.movie.data.model.Film
import com.app.movie.data.model.ItemsFilm
import com.app.movie.data.model.Result
import retrofit2.Response

interface ApiHelper {
    suspend fun getMovieDetail(movieId: String) : Response<Film>
    suspend fun getReview(movieId: String) : Response<Result>
    suspend fun getListMovie(listId : String) : Response<ItemsFilm>
}