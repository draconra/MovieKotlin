package com.draconra.moviekotlin

import android.app.Application
import com.draconra.moviekotlin.di.AppComponent
import com.draconra.moviekotlin.di.DaggerAppComponent
import com.draconra.moviekotlin.di.details.MovieDetailsModule
import com.draconra.moviekotlin.di.details.MovieDetailsSubComponent
import com.draconra.moviekotlin.di.favorites.FavoriteModule
import com.draconra.moviekotlin.di.favorites.FavoritesSubComponent
import com.draconra.moviekotlin.di.modules.AppModule
import com.draconra.moviekotlin.di.modules.DataModule
import com.draconra.moviekotlin.di.modules.NetworkModule
import com.draconra.moviekotlin.di.popular.PopularMoviesModule
import com.draconra.moviekotlin.di.popular.PopularSubComponent
import com.draconra.moviekotlin.di.search.SearchMoviesModule
import com.draconra.moviekotlin.di.search.SearchSubComponent
import com.draconra.moviekotlin.di.toprated.TopRatedMoviesModule
import com.draconra.moviekotlin.di.toprated.TopRatedSubComponent
import com.squareup.leakcanary.LeakCanary

class MovieApp: Application() {

    lateinit var appComponent: AppComponent

    private var popularMoviesComponent: PopularSubComponent? = null
    private var topRatedMoviesComponent: TopRatedSubComponent? = null
    private var favoriteMoviesComponent: FavoritesSubComponent? = null
    private var movieDetailsComponent: MovieDetailsSubComponent? = null
    private var searchMoviesComponent: SearchSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        initDependencies()
    }

    private fun initDependencies() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(applicationContext))
                .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
                .dataModule(DataModule())
                .build()

    }

    fun createPopularComponent(): PopularSubComponent {
        popularMoviesComponent = appComponent.plus(PopularMoviesModule())
        return popularMoviesComponent!!
    }
    fun releasePopularComponent() {
        popularMoviesComponent = null
    }

    fun createTopRatedComponent(): TopRatedSubComponent {
        topRatedMoviesComponent = appComponent.plus(TopRatedMoviesModule())
        return topRatedMoviesComponent!!
    }
    fun releaseTopRatedComponent() {
        topRatedMoviesComponent = null
    }

    fun createFavoritesComponent() : FavoritesSubComponent {
        favoriteMoviesComponent = appComponent.plus(FavoriteModule())
        return favoriteMoviesComponent!!
    }
    fun releaseFavoritesComponent() {
        favoriteMoviesComponent = null
    }

    fun createDetailsComponent(): MovieDetailsSubComponent {
        movieDetailsComponent = appComponent.plus(MovieDetailsModule())
        return movieDetailsComponent!!
    }
    fun releaseDetailsComponent() {
        movieDetailsComponent = null
    }

    fun createSearchComponent(): SearchSubComponent {
        searchMoviesComponent = appComponent.plus(SearchMoviesModule())
        return searchMoviesComponent!!
    }
    fun releaseSearchComponent() {
        searchMoviesComponent = null
    }
}