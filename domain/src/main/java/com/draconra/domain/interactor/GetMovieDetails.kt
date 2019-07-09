package com.draconra.domain.interactor

import com.draconra.domain.common.Transformer
import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import com.yossisegev.domain.MoviesRepository
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class GetMovieDetails(
        transformer: Transformer<Optional<MovieEntity>>,
        private val moviesRepository: MoviesRepository) : UseCase<Optional<MovieEntity>>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    fun getById(movieId: Int): Observable<Optional<MovieEntity>> {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ENTITY] = movieId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Optional<MovieEntity>> {
        val movieId = data?.get(PARAM_MOVIE_ENTITY)
        movieId?.let {
            return moviesRepository.getMovie(it as Int)
        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}