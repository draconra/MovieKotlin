package com.draconra.moviekotlin.di.toprated

import com.draconra.moviekotlin.home.popularmovies.PopularMoviesFragment
import com.draconra.moviekotlin.home.topratedmovies.TopRatedMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [TopRatedMoviesModule::class])
interface TopRatedSubComponent {
    fun inject(topRatedMoviesFragment: TopRatedMoviesFragment)
}