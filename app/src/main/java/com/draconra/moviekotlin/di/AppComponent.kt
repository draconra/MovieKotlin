package com.draconra.moviekotlin.di

import com.draconra.moviekotlin.di.details.MovieDetailsModule
import com.draconra.moviekotlin.di.details.MovieDetailsSubComponent
import com.draconra.moviekotlin.di.favorites.FavoriteModule
import com.draconra.moviekotlin.di.favorites.FavoritesSubComponent
import com.draconra.moviekotlin.home.MainActivity
import com.draconra.moviekotlin.di.modules.AppModule
import com.draconra.moviekotlin.di.modules.DataModule
import com.draconra.moviekotlin.di.modules.NetworkModule
import com.draconra.moviekotlin.di.popular.PopularMoviesModule
import com.draconra.moviekotlin.di.popular.PopularSubComponent
import com.draconra.moviekotlin.di.search.SearchMoviesModule
import com.draconra.moviekotlin.di.search.SearchSubComponent
import com.draconra.moviekotlin.di.toprated.TopRatedMoviesModule
import com.draconra.moviekotlin.di.toprated.TopRatedSubComponent
import com.draconra.moviekotlin.home.popularmovies.PopularMoviesFragment
import com.draconra.moviekotlin.home.details.MovieDetailsActivity
import com.draconra.moviekotlin.home.favorites.FavoriteMoviesFragment
import com.draconra.moviekotlin.home.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])

interface AppComponent {
    fun plus(popularMoviesModule: PopularMoviesModule): PopularSubComponent
    fun plus(topRatedMoviesModule: TopRatedMoviesModule): TopRatedSubComponent
    fun plus(favoriteMoviesModule: FavoriteModule): FavoritesSubComponent
    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent
    fun plus(searchMoviesModule: SearchMoviesModule): SearchSubComponent
}