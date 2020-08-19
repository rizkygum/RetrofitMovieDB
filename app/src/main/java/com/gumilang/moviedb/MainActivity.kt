package com.gumilang.moviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gumilang.moviedb.model.MovieResponse
import com.gumilang.moviedb.model.TvResponse
import com.gumilang.moviedb.network.ApiClient
import com.gumilang.moviedb.network.ApiHandler
import com.gumilang.moviedb.adapter.MovieAdapter
import com.gumilang.moviedb.adapter.TvAdapter
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var tvAdapter: TvAdapter

    private val repos: Repository by lazy {
        Repository(
            ApiClient.getApiBuilder()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        fetchMovies()
        fetchTv()
    }

    private fun setupRecycler(){
        movieAdapter = MovieAdapter()
        tvAdapter = TvAdapter()
        rv_movie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }

        rv_tv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvAdapter
        }
    }

    private fun fetchMovies(){
        repos.getMovies(object: ApiHandler<MovieResponse>(){
            override fun onRequestSuccess(data: MovieResponse?) {
                movieAdapter.setDataList(data?.results!!)
            }

            override fun onRequestError(errorCode: Int, errorBody: ResponseBody?) {
                setToast("Error $errorCode : ${errorBody.toString()}")
            }

            override fun onNetworkFailure(throwable: Throwable) {
                setToast(throwable.message.toString())
            }
        })
    }

    private fun fetchTv(){
        repos.getTv(object: ApiHandler<TvResponse>(){
            override fun onRequestSuccess(data: TvResponse?) {
                tvAdapter.setDataList(data?.results!!)
            }

            override fun onRequestError(errorCode: Int, errorBody: ResponseBody?) {
                setToast("Error $errorCode : ${errorBody.toString()}")
            }

            override fun onNetworkFailure(throwable: Throwable) {
                setToast(throwable.message.toString())
            }

        })
    }

    private fun setToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
