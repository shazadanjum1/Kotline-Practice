package com.kentae.kotlinepractice.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kentae.kotlinepractice.R
import com.kentae.kotlinepractice.ViewModel.MovieViewModel
import com.kentae.kotlinepractice.adapters.MovieAdapter
import com.kentae.kotlinepractice.databinding.ActivityMainBinding
import com.kentae.kotlinepractice.databinding.ActivityMvvmactivityBinding

class MVVMActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMvvmactivityBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter

    lateinit var progressDialog : ProgressDialog;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this@MVVMActivity)
        progressDialog.setTitle("Kotlin Progress Bar")
        progressDialog.setMessage("Application is loading, please wait")

        prepareRecyclerView()

        progressDialog.show()

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies("69d66957eebff9666ea46bd464773cf0")
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            progressDialog.dismiss()
            movieAdapter.setMovieList(movieList)
        })
    }

    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }

}