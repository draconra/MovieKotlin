package com.draconra.data.network

import com.draconra.data.entities.MovieData
import com.google.gson.annotations.SerializedName

class MovieListResult {

    var page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<MovieData>
}