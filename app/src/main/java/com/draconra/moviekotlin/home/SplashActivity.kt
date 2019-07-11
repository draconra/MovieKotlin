package com.draconra.moviekotlin.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.draconra.moviekotlin.R
import com.draconra.moviekotlin.base.BaseActivity


class SplashActivity : BaseActivity() {

    private val mWaitHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplash()
    }

    private fun loadSplash() {
        mWaitHandler.postDelayed({
            try {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }, 1000)
    }
}