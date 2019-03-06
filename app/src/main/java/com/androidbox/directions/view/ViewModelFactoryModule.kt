package com.androidbox.directions.view

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}