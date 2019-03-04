package com.androidbox.directions.app

import android.content.Context
import com.androidbox.directions.repository.DirectionsRepository
import dagger.Module
import dagger.Provides
// Provides all the instances to live all the application life cycle
@Module
class DirectionsAppModule(private val context: Context) {
    @Provides
    fun provideDirectionsAppContext(): Context = context

    @Provides
    fun provideRepository():DirectionsRepository = DirectionsRepository()
}