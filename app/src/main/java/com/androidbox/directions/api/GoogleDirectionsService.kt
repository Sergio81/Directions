package com.androidbox.directions.api

import com.androidbox.directions.R
import com.androidbox.directions.app.DirectionsApp
import com.androidbox.directions.model.GoogleDirections.GoogleResponse
import retrofit2.Call
import javax.inject.Singleton

@Singleton
class GoogleDirectionsService(private val googleDirectionsAPI: GoogleDirectionsAPI) {
    fun getDirections(origin:String, destination:String): Call<GoogleResponse> {
        return googleDirectionsAPI.getDirections(
            origin,
            destination,
            DirectionsApp.getContext().resources.getString(R.string.google_api_key))
    }
}