package com.draconra.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.draconra.data.entities.MovieData

@Database(entities = [MovieData::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao
}