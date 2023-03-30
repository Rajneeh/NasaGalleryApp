package com.nasa.gallery

import android.app.Application
import android.content.Context

class NasaGalleryApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: NasaGalleryApplication? = null

        fun applicationContext(): Context {
            return requireNotNull(instance).applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext()
    }
}