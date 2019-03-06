package com.androidbox.directions.view.map

import androidx.lifecycle.*
import com.androidbox.directions.model.authentication.UserApp
import com.androidbox.directions.repository.DirectionsRepository
import com.google.firebase.firestore.GeoPoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapViewModel @Inject constructor(var repository: DirectionsRepository) : ViewModel() {
    var user = MutableLiveData<UserApp>()
    var userSchedule = Transformations.switchMap(user) { u ->
        if(u.signedIn) {
            repository.updateSchedule(u.email)
            Transformations.switchMap(repository.userSchedule) { s ->
                var schedule = MutableLiveData<ArrayList<GeoPoint>>()

                schedule.value = s
                repository.getDirections()
                schedule

            }
        }else{
            MutableLiveData<ArrayList<GeoPoint>>()
        }
    }!!

    init {
            user.value = UserApp()
    }
}