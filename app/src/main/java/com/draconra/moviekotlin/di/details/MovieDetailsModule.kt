package com.draconra.moviekotlin.di.details

import com.draconra.domain.MoviesCache
import com.draconra.domain.interactor.CheckFavoriteStatus
import com.draconra.domain.interactor.GetMovieDetails
import com.draconra.domain.interactor.RemoveFavoriteMovie
import com.draconra.domain.interactor.SaveFavoriteMovie
import com.draconra.moviekotlin.common.ASyncTransformer
import com.draconra.moviekotlin.di.DI
import com.draconra.moviekotlin.home.details.MovieDetailsVMFactory
import com.draconra.moviekotlin.mapper.MovieEntityMovieMapper
import com.draconra.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieDetailsModule {

    @Provides
    fun provideRemoveFavoriteMovie(@Named(DI.favoritesCache) moviesCache: MoviesCache): RemoveFavoriteMovie {
        return RemoveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideCheckFavoriteStatus(@Named(DI.favoritesCache) moviesCache: MoviesCache): CheckFavoriteStatus {
        return CheckFavoriteStatus(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideSaveFavoriteMovieUseCase(@Named(DI.favoritesCache) moviesCache: MoviesCache): SaveFavoriteMovie {
        return SaveFavoriteMovie(ASyncTransformer(), moviesCache)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideMovieDetailsVMFactory(getMovieDetails: GetMovieDetails,
                                     saveFavoriteMovie: SaveFavoriteMovie,
                                     removeFavoriteMovie: RemoveFavoriteMovie,
                                     checkFavoriteStatus: CheckFavoriteStatus,
                                     mapper: MovieEntityMovieMapper): MovieDetailsVMFactory {
        return MovieDetailsVMFactory(getMovieDetails, saveFavoriteMovie, removeFavoriteMovie, checkFavoriteStatus, mapper)
    }
}