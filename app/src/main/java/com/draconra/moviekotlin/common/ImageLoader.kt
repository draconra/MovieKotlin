package com.draconra.moviekotlin.common

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, imageView: ImageView, fadeEffect: Boolean = true)
}

