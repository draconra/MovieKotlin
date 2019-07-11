package com.draconra.moviekotlin.home.topratedmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.GetPopularMovies
import com.draconra.domain.interactor.GetTopRatedMovies
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.model.Movie

class TopRatedMoviesVMFactory(private val useCase: GetTopRatedMovies,
                              private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMoviesViewModel(useCase, mapper) as T
    }

}