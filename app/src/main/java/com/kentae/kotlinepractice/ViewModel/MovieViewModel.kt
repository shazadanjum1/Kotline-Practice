package com.kentae.kotlinepractice.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kentae.kotlinepractice.models.Movies
import com.kentae.kotlinepractice.repositories.MovieRepository
import com.kentae.kotlinepractice.retrofit3.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    //private var movieLiveData = MutableLiveData<List<com.kentae.kotlinepractice.models.Result>>()
    val movieRepository : MovieRepository = MovieRepository();

    fun getPopularMovies(key : String) {
        movieRepository.getPopularMovies(key);
    }

    fun observeMovieLiveData() : LiveData<List<com.kentae.kotlinepractice.models.Result>> {
        //return movieLiveData
        return movieRepository.observeMovieLiveData();
    }
}
