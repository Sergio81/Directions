package com.androidbox.directions.view

import androidx.lifecycle.ViewModel
import com.androidbox.directions.view.map.MapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    internal abstract fun bindMainViewModel(editPlaceViewModel: MapViewModel): ViewModel
}