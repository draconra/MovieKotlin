package com.draconra.moviekotlin.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.draconra.moviekotlin.MovieApp
import com.draconra.moviekotlin.di.AppComponent

open class BaseActivity : AppCompatActivity() {

    protected fun getMovieApp(): MovieApp = (application as MovieApp)

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}