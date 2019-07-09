package com.draconra.data.db

import androidx.room.*
import com.draconra.data.entities.MovieData

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getFavorites(): List<MovieData>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun get(movieId: Int): MovieData?

    @Query("SELECT * FROM movies WHERE title LIKE :query")
    fun search(query: String): List<MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllMovies(movies: List<MovieData>)

    @Delete
    fun removeMovie(movie: MovieData)

    @Query("DELETE FROM movies")
    fun clear()

}