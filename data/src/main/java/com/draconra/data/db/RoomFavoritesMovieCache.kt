package com.draconra.data.db

import com.draconra.data.entities.MovieData
import com.draconra.domain.MoviesCache
import com.draconra.domain.common.Mapper
import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

class RoomFavoritesMovieCache(database: MoviesDatabase,
                              private val entityToDataMapper: Mapper<MovieEntity, MovieData>,
                              private val dataToEntityMapper: Mapper<MovieData, MovieEntity>) : MoviesCache {
    private val dao: MoviesDao = database.getMoviesDao()

    override fun clear() {
        dao.clear()
    }

    override fun save(movieEntityEntity: MovieEntity) {
        dao.saveMovie(entityToDataMapper.mapFrom(movieEntityEntity))
    }

    override fun remove(movieEntityEntity: MovieEntity) {
        dao.removeMovie(entityToDataMapper.mapFrom(movieEntityEntity))
    }

    override fun saveAll(movieEntityEntities: List<MovieEntity>) {
        dao.saveAllMovies(movieEntityEntities.map { entityToDataMapper.mapFrom(it) })
    }

    override fun getAll(): Observable<List<MovieEntity>> {
        return Observable.fromCallable { dao.getFavorites().map { dataToEntityMapper.mapFrom(it) } }
    }

    override fun get(movieId: Int): Observable<Optional<MovieEntity>> {

        return Observable.fromCallable {
            val movieData = dao.get(movieId)
            movieData?.let {
                Optional.of(dataToEntityMapper.mapFrom(it))
            } ?: Optional.empty()
        }
    }

    override fun isEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getFavorites().isEmpty() }
    }

    override fun search(query: String): Observable<List<MovieEntity>> {
        val searchQuery = "%$query%"
        return Observable.fromCallable {
            dao.search(searchQuery).map { dataToEntityMapper.mapFrom(it) }
        }
    }
}