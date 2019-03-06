package com.androidbox.directions.app

import android.app.Application
import android.content.Context

class DirectionsApp : Application() {
    val appComponent: DirectionsAppComponent by lazy {
        DaggerDirectionsAppComponent
            .builder()
            .build()
    }

    init { instance = this }

    companion object {
        private lateinit var instance: DirectionsApp

        fun getContext(): Context = instance
    }
}