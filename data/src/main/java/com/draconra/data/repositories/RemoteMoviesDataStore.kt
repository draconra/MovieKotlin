package com.draconra.data.repositories

import com.draconra.data.mappers.DetailsDataMovieEntityMapper
import com.draconra.data.mappers.MovieDataEntityMapper
import com.draconra.data.network.ApiService
import com.draconra.domain.MoviesDataStore
import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

class RemoteMoviesDataStore(private val api: ApiService) : MoviesDataStore {

    private val movieDataMapper = MovieDataEntityMapper()
    private val detailedDataMapper = DetailsDataMovieEntityMapper()

    override fun search(query: String): Observable<List<MovieEntity>> {
        return api.searchMovies(query).map { results ->
            results.movies.map { movieDataMapper.mapFrom(it) }
        }
    }

    override fun getMovies(): Observable<List<MovieEntity>> {
        return api.getPopularMovies().map { results ->
            results.movies.map { movieDataMapper.mapFrom(it) }
        }
    }

    override fun getMovieById(movieId: Int): Observable<Optional<MovieEntity>> {
        return api.getMovieDetails(movieId).flatMap { detailedData ->
            Observable.just(Optional.of(detailedDataMapper.mapFrom(detailedData)))
        }
    }
}