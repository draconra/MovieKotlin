package com.draconra.moviekotlin.di.popular

import com.draconra.domain.interactor.GetPopularMovies
import com.draconra.moviekotlin.common.ASyncTransformer
import com.draconra.moviekotlin.home.popularmovies.PopularMoviesVMFactory
import com.draconra.moviekotlin.mapper.MovieEntityMovieMapper
import com.draconra.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

@PopularScope
@Module
class PopularMoviesModule {
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMovies {
        return GetPopularMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun providePopularMoviesVMFactory(useCase: GetPopularMovies, mapper: MovieEntityMovieMapper): PopularMoviesVMFactory {
        return PopularMoviesVMFactory(useCase, mapper)
    }
}