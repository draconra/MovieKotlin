package com.draconra.data.network

import com.draconra.data.entities.DetailsData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}?append_to_response=videoEntities,reviewEntities")
    fun getMovieDetails(@Path("id") movieId: Int): Observable<DetailsData>

    @GET("movie/popular") ///movie/now_playing
    fun getPopularMovies(): Observable<MovieListResult>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Observable<MovieListResult>

}