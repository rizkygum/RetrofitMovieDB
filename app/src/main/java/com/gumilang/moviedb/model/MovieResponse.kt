package com.gumilang.moviedb.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@SerializedName("page")
	val page: Int? = 0,

	@SerializedName("total_pages")
	val totalPages: Int? = 0,

	@SerializedName("results")
	val results: List<Movies>? = emptyList(),

	@SerializedName("total_results")
	val totalResults: Int? = 0
)