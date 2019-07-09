package com.draconra.moviekotlin.home.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.GetPopularMovies
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.model.Movie

class PopularMoviesVMFactory(private val useCase: GetPopularMovies,
                             private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase, mapper) as T
    }

}