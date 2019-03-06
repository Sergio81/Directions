package com.androidbox.directions.app

import dagger.Component
import javax.inject.Singleton
import android.app.Application
import com.androidbox.directions.api.NetworkModule
import com.androidbox.directions.repository.DirectionsRepository
import com.androidbox.directions.view.MainActivity
import com.androidbox.directions.view.ViewModelFactoryModule
import com.androidbox.directions.view.ViewModelModule
import com.androidbox.directions.view.map.MapFragment

@Singleton
@Component(modules=[DirectionsAppModule::class, NetworkModule::class,
    ViewModelFactoryModule::class, ViewModelModule::class])
interface DirectionsAppComponent {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun repository(directionsRepository: DirectionsRepository)
//        fun build(): DirectionsAppComponent
//    }

    //fun inject(app: DirectionsApp)
    fun inject(mainActivity: MainActivity)
    fun inject(mapFragment: MapFragment)
    //fun inject(mainActivity: MainActivity)
}