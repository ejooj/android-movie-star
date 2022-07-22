package com.podium.technicalchallenge.base

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class BaseApplication: Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(applicationContext)
        .memoryCache {
            MemoryCache.Builder(applicationContext)
                .maxSizePercent(0.25)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(applicationContext.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .build()
}