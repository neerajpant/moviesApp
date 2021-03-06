package com.example.greedygame.ui.movDetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.greedygame.api.NetworkApi
import com.example.greedygame.model.MovieDetailResponse
import com.example.greedygame.model.Resource
import com.example.greedygame.model.ReviewResponse
import com.example.greedygame.repo.GreedyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: GreedyRepository,
    private val api: NetworkApi
):ViewModel() {
    private var movie_Details=  MutableLiveData<Resource<MovieDetailResponse>>()
    public val movieDetails get() = movie_Details
    private var review_Details=  MutableLiveData<Resource<List<ReviewResponse>>>()
    public val reviewDetails get() = review_Details

    fun getMoviesDetails(id: Int?) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            println("before serverCall")
            val response =  repository.getMoviesDetailResponse(id)
            println("MovieDetailViewModel ${response.status}")


            emit(Resource.success(data = response))
        } catch (exception: Exception) {
            println("exception ${exception.message}")
            println("cause ${exception.cause.toString()}")
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getMoviesDetailsTest(id: Int?)
    {
        movie_Details.value= Resource.loading(null)
        println("getMoviesDetailsTest")
        viewModelScope.launch(Dispatchers.IO) {
            println("getMoviesDetailsTest::before server call")
         try {
             movie_Details.postValue(Resource.success(repository.getMoviesDetailResponse(id)))
         }catch (err:Exception)
         {
             movie_Details.value= Resource.error(null,message = err.message?:"Error occured")
         }
        }
    }
    fun getReviewDetails(id: Int?)
    {
        review_Details.value= Resource.loading(null)
        println("getMoviesDetailsTest")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result=repository.getMoviesReviewResponse(id)
                review_Details.postValue(Resource.success(result.result!!))
            }catch (err:Exception)
            {
                review_Details.value= Resource.error(null,message = err.message?:"Error occured")
            }
        }
    }


}
