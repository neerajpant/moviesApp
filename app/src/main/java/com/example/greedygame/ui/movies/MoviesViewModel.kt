package com.example.greedygame.ui.movies

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.greedygame.api.NetworkApi
import com.example.greedygame.model.Resource
import com.example.greedygame.repo.GreedyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel@ViewModelInject constructor(
    private val repository: GreedyRepository,
    private val api:NetworkApi
) : ViewModel() {
    init {
        println("MovieViewModel::Init")
        /* viewModelScope.launch(Dispatchers.IO) {
           val result= repository.getMovies()
            println("MovieViewModel ${result.size}")
        }*/
    }



/*    val listData = Pager(PagingConfig(pageSize = 6)) {
        PostDataSource(apiService)
    }.flow.cachedIn(viewModelScope)*/

    val moviesData = repository.getMoviesRepsonse()




       /* fun getMovies() {
            viewModelScope.launch(Dispatchers.IO) {
                val result = repository.getMoviesRepsonse()
                println("MovieViewModel ${result.movieResponse.size}")
            }
        }*/

}