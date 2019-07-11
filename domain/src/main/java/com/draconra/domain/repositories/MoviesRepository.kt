package com.draconra.domain.repositories

import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

interface MoviesRepository {
    fun getMovies(type: String): Observable<List<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
    fun getMovie(movieId: Int): Observable<Optional<MovieEntity>>
}