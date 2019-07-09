package com.draconra.domain.interactor

import com.draconra.domain.MoviesCache
import com.draconra.domain.common.Transformer
import com.draconra.domain.model.MovieEntity
import io.reactivex.Observable

class GetFavoriteMovies(transformer: Transformer<List<MovieEntity>>,
                        private val moviesCache: MoviesCache): UseCase<List<MovieEntity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieEntity>> {
        return moviesCache.getAll()
    }

}