package com.draconra.moviekotlin.di.details

import com.draconra.moviekotlin.home.details.MovieDetailsActivity
import dagger.Subcomponent

@DetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}