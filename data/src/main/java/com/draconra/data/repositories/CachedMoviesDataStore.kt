package com.draconra.data.repositories

import com.draconra.domain.MoviesCache
import com.draconra.domain.MoviesDataStore
import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

class CachedMoviesDataStore(private val moviesCache: MoviesCache): MoviesDataStore {

    override fun search(query: String): Observable<List<MovieEntity>> {
        return moviesCache.search(query)
    }

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return moviesCache.get(movieId)
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

    fun isEmpty(): Observable<Boolean> {
        return moviesCache.isEmpty()
    }

    fun saveAll(movieEntityEntities: List<MovieEntity>) {
        moviesCache.saveAll(movieEntityEntities)
    }
}