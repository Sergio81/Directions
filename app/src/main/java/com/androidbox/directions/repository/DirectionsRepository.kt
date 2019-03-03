package com.androidbox.directions.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DirectionsRepository {
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
}