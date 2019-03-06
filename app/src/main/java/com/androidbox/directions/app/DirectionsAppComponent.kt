package com.androidbox.directions.app

import dagger.Component
import javax.inject.Singleton
import android.app.Application
import com.androidbox.directions.repository.DirectionsRepository
import com.androidbox.directions.view.MainActivity
import dagger.BindsInstance

@Singleton
@Component(modules=[DirectionsAppModule::class])
interface DirectionsComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun repository(directionsRepository: DirectionsRepository)
        fun build(): DirectionsComponent
    }

    fun inject(app: DirectionsApp)
    fun inject(mainActivity: MainActivity)
    //fun inject(mainActivity: MainActivity)
}