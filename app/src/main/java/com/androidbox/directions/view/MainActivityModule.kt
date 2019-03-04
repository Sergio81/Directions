package com.androidbox.directions.view

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideMainView(mainActivity: MainActivity): MainActivity {
        return mainActivity
    }
}