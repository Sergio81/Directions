package com.androidbox.directions.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.androidbox.directions.api.GoogleDirectionsService
import com.androidbox.directions.model.GoogleDirections.GoogleResponse
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DirectionsRepository @Inject constructor(private val directionsService: GoogleDirectionsService) {
    private val db = FirebaseFirestore.getInstance()
    val userSchedule = MutableLiveData<ArrayList<GeoPoint>>()

    fun updateSchedule(userId:String) {
        val formattedDate = SimpleDateFormat("MMMMyy", Locale.US).format(Calendar.getInstance().time)

        db.document("Users/$userId/Schedules/$formattedDate")
            .get().addOnSuccessListener{document ->
                if(document.exists()){
                    Log.d("this_data", "DocumentSnapshot data: " + document.data!!["schedule"].toString())
                    val positionsArray = ArrayList<GeoPoint>()

                    (document.data!!["schedule"] as ArrayList<*>).forEach { if(it is GeoPoint) positionsArray.add(it) }

                    userSchedule.value = positionsArray
                }
            }
    }

    fun getDirections() {
        val points = userSchedule.value!!

        directionsService.getDirections(
            "${points[0].latitude},${points[0].longitude}",
            "${points[1].latitude},${points[1].longitude}"
        ).enqueue(object : Callback<GoogleResponse> {
            override fun onFailure(call: Call<GoogleResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<GoogleResponse>?, response: Response<GoogleResponse>?) {
                Log.v("retrofit", response!!.body()!!.status)
            }

        })
//
//            return Transformations.switchMap(directionsService.getDirections(
//                "${points[0].latitude},${points[0].longitude}",
//                "${points[1].latitude},${points[1].longitude}"
//            )){
//                val response = MutableLiveData<String>()
//                if(it.isNotEmpty())
//                    response.value = it[0].status
//
//                response
//            }
    }
}