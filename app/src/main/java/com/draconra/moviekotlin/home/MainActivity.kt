package com.draconra.moviekotlin.home

import android.os.Bundle
import android.view.MenuItem
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseActivity
import com.draconra.moviekotlin.home.favorites.FavoriteMoviesFragment
import com.draconra.moviekotlin.home.popularmovies.PopularMoviesFragment
import com.draconra.moviekotlin.home.search.SearchFragment
import com.draconra.moviekotlin.home.topratedmovies.TopRatedMoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PopularMoviesFragment(), "popular")
                    .commitNow()
            title = getString(R.string.popular)
        }

        navigationBar = bottomNavigationView as BottomNavigationView
        navigationBar.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == navigationBar.selectedItemId) {
            return false
        }
        when (item.itemId) {
            R.id.action_popular -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, PopularMoviesFragment(), "popular")
                        .commitNow()
                title = getString(R.string.popular)
            }

            R.id.action_favorites -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FavoriteMoviesFragment(), "favorites")
                        .commitNow()
                title = getString(R.string.my_favorites)
            }

            R.id.action_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment(), "search")
                        .commitNow()
                title = getString(R.string.search)
            }
        }
        return true
    }

}
