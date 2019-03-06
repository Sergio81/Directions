package com.androidbox.directions.api

import com.androidbox.directions.BuildConfig
import com.androidbox.directions.repository.DirectionsRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, factory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .client(okHttpClient)
            .addConverterFactory(factory)
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun providesAPI(retrofit: Retrofit) : GoogleDirectionsAPI{
        return retrofit.create<GoogleDirectionsAPI>(GoogleDirectionsAPI::class.java)
    }

    @Provides
    @Singleton
    internal fun providesDirectionsService(googleDirectionsAPI: GoogleDirectionsAPI) :GoogleDirectionsService{
        return GoogleDirectionsService(googleDirectionsAPI)
    }

    @Provides
    @Singleton
    internal fun provideRepository(directionsService: GoogleDirectionsService) : DirectionsRepository{
        return DirectionsRepository(directionsService)
    }
}