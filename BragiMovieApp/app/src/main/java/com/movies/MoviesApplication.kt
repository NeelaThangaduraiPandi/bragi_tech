package com.movies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoviesApplication : Application() {
    companion object {
        private const val TAG = "MoviesApplication"
    }
}