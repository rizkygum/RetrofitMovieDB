package com.gumilang.moviedb

import com.gumilang.moviedb.model.MovieResponse
import com.gumilang.moviedb.model.TvResponse
import com.gumilang.moviedb.network.ApiHandler
import com.gumilang.moviedb.network.ApiInterface
import com.gumilang.moviedb.network.ApiUrl

class Repository(private val restApi: ApiInterface) {

    fun getMovies(handler: ApiHandler<MovieResponse>){
        restApi.getDiscoverMovies(ApiUrl.TOKEN, "popularity.desc",1)
            .enqueue(handler)
    }

    fun getTv(handler: ApiHandler<TvResponse>){
        restApi.getDiscoverTv(ApiUrl.TOKEN, "popularity.desc", 1)
            .enqueue(handler)
    }
}