package com.example.greedygame.paging

import androidx.paging.PagingSource
import com.example.greedygame.api.NetworkApi
import com.example.greedygame.model.MovieResponse

import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val unsplashApi: NetworkApi

) : PagingSource<Int, MovieResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX

        return try {
            val response = unsplashApi.getMValue( position, params.loadSize)
            val movies = response.movieResponse
            println("MoviePagingSource ${movies.size}")

            LoadResult.Page(
                data = movies,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}