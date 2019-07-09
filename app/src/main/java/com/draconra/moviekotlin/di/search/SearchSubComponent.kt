package com.draconra.moviekotlin.di.search

import com.draconra.moviekotlin.home.search.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchMoviesModule::class])
interface SearchSubComponent {
    fun inject(searchFragment: SearchFragment)
}