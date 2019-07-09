package com.draconra.moviekotlin.di.modules

import android.content.Context
import com.draconra.moviekotlin.common.GlideImageLoader
import com.draconra.moviekotlin.common.ImageLoader
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = context

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideImageLoader(context: Context) : ImageLoader {
        return GlideImageLoader(context)
    }
}