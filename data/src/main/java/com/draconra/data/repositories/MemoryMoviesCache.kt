package com.draconra.data.repositories

import com.draconra.domain.MoviesCache
import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

class MemoryMoviesCache : MoviesCache {

    private val movies: LinkedHashMap<Int, MovieEntity> = LinkedHashMap()

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { movies.isEmpty() }
    }

    override fun remove(movieEntityEntity: MovieEntity) {
        movies.remove(movieEntityEntity.id)
    }

    override fun clear() {
        movies.clear()
    }

    override fun save(movieEntityEntity: MovieEntity) {
        movies[movieEntityEntity.id] = movieEntityEntity
    }

    override fun saveAll(movieEntityEntities: List<MovieEntity>) {
        movieEntityEntities.forEach { movieEntity -> this.movies[movieEntity.id] = movieEntity }
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.just(movies.values.toList())
    }

    override fun get(movieId: Int): Observable<Optional<MovieEntity>> {
        return Observable.just(Optional.of(movies[movieId]))
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        return Observable.fromCallable {
            movies.values.filter {
                it.title.contains(query) || it.originalTitle.contains(query)
            }
        }
    }
}

