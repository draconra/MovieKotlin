package com.draconra.domain.interactor

import com.draconra.domain.common.Transformer
import com.draconra.domain.model.MovieEntity
import com.yossisegev.domain.MoviesRepository
import io.reactivex.Observable

open class GetPopularMovies(transformer: Transformer<List<MovieEntity>>,
                            private val moviesRepository: MoviesRepository) : UseCase<List<MovieEntity>>(transformer) {
    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesRepository.getMovies()
    }

}