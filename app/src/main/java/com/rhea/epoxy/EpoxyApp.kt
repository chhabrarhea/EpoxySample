package com.rhea.epoxy

import android.app.Application
import coil.ImageLoader
import coil.util.CoilUtils
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class EpoxyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        ImageLoader.Builder(applicationContext)
            .crossfade(true)

    }
}