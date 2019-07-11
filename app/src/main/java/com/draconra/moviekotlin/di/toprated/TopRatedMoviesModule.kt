package com.draconra.moviekotlin.di.toprated

import com.draconra.domain.interactor.GetTopRatedMovies
import com.draconra.domain.repositories.MoviesRepository
import com.draconra.moviekotlin.common.ASyncTransformer
import com.draconra.moviekotlin.home.topratedmovies.TopRatedMoviesVMFactory
import com.draconra.moviekotlin.mapper.MovieEntityMovieMapper
import dagger.Module
import dagger.Provides

@TopRatedScope
@Module
class TopRatedMoviesModule {
    @Provides
    fun provideGetTopRatedMoviesUseCase(moviesRepository: MoviesRepository): GetTopRatedMovies {
        return GetTopRatedMovies(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideTopRatedMoviesVMFactory(useCase: GetTopRatedMovies, mapper: MovieEntityMovieMapper): TopRatedMoviesVMFactory {
        return TopRatedMoviesVMFactory(useCase, mapper)
    }
}