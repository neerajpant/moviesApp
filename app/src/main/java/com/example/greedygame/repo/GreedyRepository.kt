package com.example.greedygame.repo


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.greedygame.api.NetworkApi
import com.example.greedygame.model.*
import com.example.greedygame.paging.MoviesPagingSource

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GreedyRepository@Inject constructor(private val networkApi: NetworkApi) {


    fun getMoviesRepsonse() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(networkApi) }
        ).liveData
    suspend fun getMoviesDetailResponse(id:Int?):MovieDetailResponse{

        val response=networkApi.getMovieDetail(id)
        println("getMoviesDetailResponse ${response.production_companies?.size}")

        return response
    }
    suspend fun getMoviesReviewResponse(id:Int?):ReviewsData{

        val response=networkApi.getReviewDetail(id)
        println("getMoviesReviewResponse ${response.result}")

        return response
    }
    suspend fun getViewPagerData(id:Int?):MovieResponseData{

        val response=networkApi.getMoviePagerValue(id)

        println("getViewPagerData ${response.movieResponse.size}")
        return response
    }

}