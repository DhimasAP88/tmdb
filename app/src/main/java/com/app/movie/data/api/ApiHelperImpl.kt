package com.app.movie.data.api

import com.app.movie.BuildConfig
import com.app.movie.data.model.Film
import com.app.movie.data.model.ItemsFilm
import com.app.movie.data.model.Result
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getMovieDetail(movieId: String): Response<Film> {
        return apiService.movieDetail(movieId, BuildConfig.API_KEY)
    }

    override suspend fun getReview(movieId: String): Response<Result> {
        return apiService.movieReview(movieId, BuildConfig.API_KEY)
    }

    override suspend fun getListMovie(listId: String): Response<ItemsFilm> {
        return apiService.listMovie(listId, BuildConfig.API_KEY)
    }


}