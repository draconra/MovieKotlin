package com.draconra.moviekotlin.di.favorites

import com.draconra.domain.MoviesCache
import com.draconra.domain.interactor.GetFavoriteMovies
import com.draconra.moviekotlin.common.ASyncTransformer
import com.draconra.moviekotlin.di.DI
import com.draconra.moviekotlin.home.favorites.FavoriteMoviesVMFactory
import com.draconra.moviekotlin.mapper.MovieEntityMovieMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FavoriteModule {

    @Provides
    fun provideGetFavoriteMovies(@Named(DI.favoritesCache) moviesCache: MoviesCache): GetFavoriteMovies {
        return GetFavoriteMovies(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideFavoriteMoviesVMFactory(getFavoriteMovies: GetFavoriteMovies,
                                       movieEntityMoveMapper: MovieEntityMovieMapper): FavoriteMoviesVMFactory {
        return FavoriteMoviesVMFactory(getFavoriteMovies, movieEntityMoveMapper)
    }
}