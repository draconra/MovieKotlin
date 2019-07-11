package com.draconra.moviekotlin.di.modules

import android.content.Context
import androidx.room.Room
import com.draconra.data.db.MoviesDatabase
import com.draconra.data.db.RoomFavoritesMovieCache
import com.draconra.data.mappers.MovieDataEntityMapper
import com.draconra.data.mappers.MovieEntityDataMapper
import com.draconra.data.network.ApiService
import com.draconra.data.repositories.CachedMoviesDataStore
import com.draconra.data.repositories.MemoryMoviesCache
import com.draconra.data.repositories.MoviesRepositoryImpl
import com.draconra.data.repositories.RemoteMoviesDataStore
import com.draconra.domain.MoviesCache
import com.draconra.moviekotlin.di.DI
import com.draconra.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
@Singleton
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies_db").build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiService,
                               @Named(DI.inMemoryCache) cache: MoviesCache): MoviesRepository {

        val cachedMoviesDataStore = CachedMoviesDataStore(cache)
        val remoteMoviesDataStore = RemoteMoviesDataStore(api)
        return MoviesRepositoryImpl(cachedMoviesDataStore, remoteMoviesDataStore)
    }

    @Singleton
    @Provides
    @Named(DI.inMemoryCache)
    fun provideInMemoryMoviesCache(): MoviesCache {
        return MemoryMoviesCache()
    }

    @Singleton
    @Provides
    @Named(DI.favoritesCache)
    fun provideFavoriteMoviesCache(moviesDatabase: MoviesDatabase,
                                   entityDataMapper: MovieEntityDataMapper,
                                   dataEntityMapper: MovieDataEntityMapper): MoviesCache {
        return RoomFavoritesMovieCache(moviesDatabase, entityDataMapper, dataEntityMapper)
    }
}