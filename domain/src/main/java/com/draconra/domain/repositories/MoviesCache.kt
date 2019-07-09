package com.draconra.domain

import com.draconra.domain.model.MovieEntity
import com.draconra.domain.model.Optional
import io.reactivex.Observable

interface MoviesCache {

    fun clear()
    fun save(movieEntityEntity: MovieEntity)
    fun remove(movieEntityEntity: MovieEntity)
    fun saveAll(movieEntityEntities: List<MovieEntity>)
    fun getAll(): Observable<List<MovieEntity>>
    fun get(movieId: Int): Observable<Optional<MovieEntity>>
    fun search(query: String): Observable<List<MovieEntity>>
    fun isEmpty(): Observable<Boolean>
}