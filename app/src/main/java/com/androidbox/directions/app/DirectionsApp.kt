package com.androidbox.directions.app

import android.app.Application

class DirectionsApp : Application() {
    val appComponent by lazy {
        DaggerDirectionsComponent
            .builder()
            //.roomModule(RoomModule(this))
            .build()
    }
}