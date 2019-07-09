package com.draconra.moviekotlin.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class GlideImageLoader(private val context: Context) : ImageLoader {

    override fun load(url: String, imageView: ImageView, fadeEffect: Boolean) {
        if (fadeEffect)
            Glide.with(context).load(url).into(imageView)
        else
            Glide.with(context).load(url).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
}