package com.androidbox.directions.app

import android.content.Context
import com.androidbox.directions.repository.DirectionsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

// Provides all the instances to live all the application life cycle
@Module
class DirectionsAppModule(val context: Context) {
    @Provides
    fun provideDirectionsAppContext(): Context = context
}