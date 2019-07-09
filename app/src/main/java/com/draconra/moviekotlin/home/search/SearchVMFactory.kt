package com.draconra.moviekotlin.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.draconra.domain.common.Mapper
import com.draconra.domain.interactor.SearchMovie
import com.draconra.domain.model.MovieEntity
import com.draconra.moviekotlin.model.Movie


class SearchVMFactory(private val searchMovie: SearchMovie,
                      private val mapper: Mapper<MovieEntity, Movie>): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(searchMovie, mapper) as T
    }

}