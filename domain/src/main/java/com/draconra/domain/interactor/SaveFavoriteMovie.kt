package com.draconra.domain.interactor

import com.draconra.domain.MoviesCache
import com.draconra.domain.common.Transformer
import com.draconra.domain.model.MovieEntity
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class SaveFavoriteMovie(transformer: Transformer<Boolean>,
                        private val moviesCache: MoviesCache): UseCase<Boolean>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movieEntity"
    }

    override fun createObservable(data: Map<String, Any>?): Observable<Boolean> {

        val movieEntity = data?.get(PARAM_MOVIE_ENTITY)

        movieEntity?.let {
            return Observable.fromCallable {
                val entity = it as MovieEntity
                moviesCache.save(entity)
                return@fromCallable true
            }
        }?: return Observable.error { IllegalArgumentException("MovieEntity must be provided.") }

    }

    fun save(movieEntityEntity: MovieEntity): Observable<Boolean> {
        val data = HashMap<String, MovieEntity>()
        data[PARAM_MOVIE_ENTITY] = movieEntityEntity
        return observable(data)
    }
}