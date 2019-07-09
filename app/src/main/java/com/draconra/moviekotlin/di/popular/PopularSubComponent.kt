package com.draconra.moviekotlin.di.popular

import com.draconra.moviekotlin.home.popularmovies.PopularMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [PopularMoviesModule::class])
interface PopularSubComponent {
    fun inject(popularMoviesFragment: PopularMoviesFragment)
}