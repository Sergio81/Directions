package com.androidbox.directions.app

import android.app.Activity
import dagger.multibindings.IntoMap
import dagger.Binds
import com.androidbox.directions.view.MainActivity
import com.androidbox.directions.view.MainActivityComponent
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector


@Module
abstract class ActivityBuilder {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}