package com.app.movie.data.api

import com.app.movie.data.model.Film
import com.app.movie.data.model.ItemsFilm
import com.app.movie.data.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{movie_id}")
    suspend fun movieDetail(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
    ) : Response<Film>

    @GET("movie/{movie_id}/reviews")
    suspend fun movieReview(
        @Path("movie_id") movieId : String,
        @Query("api_key") apiKey : String
    ) : Response<Result>

    @GET("list/{list_id}")
    suspend fun listMovie(
        @Path("list_id") listId : String,
        @Query("api_key") apiKey : String
    ) : Response<ItemsFilm>
}