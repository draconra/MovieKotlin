package com.draconra.moviekotlin.di.search

import com.draconra.domain.interactor.SearchMovie
import com.draconra.moviekotlin.common.ASyncTransformer
import com.draconra.moviekotlin.home.search.SearchVMFactory
import com.draconra.moviekotlin.mapper.MovieEntityMovieMapper
import com.draconra.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class SearchMoviesModule {

    @Provides
    fun provideSearchMovieUseCase(moviesRepository: MoviesRepository): SearchMovie {
        return SearchMovie(ASyncTransformer(), moviesRepository)
    }

    @Provides
    fun provideSearchVMFactory(useCase: SearchMovie, mapper: MovieEntityMovieMapper): SearchVMFactory {
        return SearchVMFactory(useCase, mapper)
    }
}