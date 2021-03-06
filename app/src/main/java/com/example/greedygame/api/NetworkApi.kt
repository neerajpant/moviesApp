package com.example.greedygame.api

import com.example.greedygame.BuildConfig
import com.example.greedygame.model.*
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val CLIENT_ID = "109aa89aed2c24fdd695829f4217e304"
        const val SMALL_IMAGE = "https://image.tmdb.org/t/p/w200"
    }

    @GET("3/discover/movie")
  suspend  fun getMValue(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ):MovieResponseData
    @GET("3/movie/{movie_id}")
    suspend  fun getMovieDetail(
        @Path("movie_id") movie: Int?
    ):MovieDetailResponse
    @GET("3/movie/{movie_id}/reviews")
    suspend  fun getReviewDetail(
        @Path("movie_id") movie: Int?
    ):ReviewsData
    @GET("3/discover/movie")
    suspend  fun getMoviePagerValue(
        @Query("page") page: Int?):MovieResponseData
}