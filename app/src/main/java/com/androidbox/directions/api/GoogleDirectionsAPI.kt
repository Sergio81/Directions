package com.androidbox.directions.api

import androidx.lifecycle.LiveData
import com.androidbox.directions.model.GoogleDirections.GoogleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleDirectionsAPI {
    @GET("json?")
    fun getDirections(
        @Query("origin")origin:String,
        @Query("destination")destination:String,
        @Query("key")key:String
    ): LiveData<List<GoogleResponse>>
}