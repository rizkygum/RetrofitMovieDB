package com.gumilang.moviedb.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var retrofit: Retrofit? = null

    fun getApiBuilder(): ApiInterface {
        return retrofit()!!.create(ApiInterface::class.java)
    }

    private fun retrofit(): Retrofit? {

        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit
    }

}