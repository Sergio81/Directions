package com.androidbox.directions.app

import dagger.Component
import javax.inject.Singleton
import android.app.Application
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule

@Singleton
@Component(modules=[AndroidInjectionModule::class, DirectionsAppModule::class])
interface DirectionsComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): DirectionsComponent
    }

    fun inject(app: DirectionsApp)
    //fun inject(mainActivity: MainActivity)
}