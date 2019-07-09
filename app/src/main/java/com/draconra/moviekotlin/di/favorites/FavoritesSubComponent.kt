package com.draconra.moviekotlin.di.favorites

import com.draconra.moviekotlin.home.favorites.FavoriteMoviesFragment
import dagger.Subcomponent

@FavoritesScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoritesSubComponent {
    fun inject(favoriteMoviesFragment: FavoriteMoviesFragment)
}