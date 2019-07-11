package com.draconra.data.repositories

import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import com.draconra.domain.repositories.MoviesRepository
import io.reactivex.Observable

class MoviesRepositoryImpl(private val cachedDataStore: CachedMoviesDataStore,
                           private val remoteDataStore: RemoteMoviesDataStore) : MoviesRepository {

    override fun getMovies(): Observable<List<MovieEntity>> {

        return cachedDataStore.isEmpty().flatMap { empty ->
            if (!empty) {
                return@flatMap cachedDataStore.getMovies()
            }
            else {
                return@flatMap remoteDataStore.getMovies()
                                              .doOnNext { movies ->
                                                  cachedDataStore.saveAll(movies)
                                              }
            }
        }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        return remoteDataStore.search(query)
    }

    override fun getMovie(movieId: Int): Observable<Optional<MovieEntity>> {
        return remoteDataStore.getMovieById(movieId)
    }

}