package com.androidbox.directions.api

import androidx.lifecycle.LiveData
import com.androidbox.directions.R
import com.androidbox.directions.app.DirectionsApp
import com.androidbox.directions.model.GoogleDirections.GoogleResponse
import javax.inject.Singleton

@Singleton
class GoogleDirectionsService(private val googleDirectionsAPI: GoogleDirectionsAPI) {
    fun getDirections(origin:String, destination:String): LiveData<List<GoogleResponse>> {
        return googleDirectionsAPI.getDirections(
            origin,
            destination,
            DirectionsApp.getContext().resources.getString(R.string.google_api_key))
    }
}