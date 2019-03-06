package com.androidbox.directions.view.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androidbox.directions.R
import com.androidbox.directions.app.DirectionsApp
import com.androidbox.directions.repository.DirectionsRepository
import com.androidbox.directions.view.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.GeoPoint
import kotlinx.android.synthetic.main.fragment_map.*
import com.google.android.gms.maps.model.LatLngBounds
import java.util.*
import javax.inject.Inject

class MapFragment : Fragment(), OnMapReadyCallback {
    private val auth = FirebaseAuth.getInstance()
    private var markerCity: Marker? = null
    private var mMap: GoogleMap? = null
    private var isMapReady = false
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MapViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity!!.application as DirectionsApp).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(null)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        isMapReady = true
        viewModel.userSchedule.observe(this, Observer{ points ->
            showPoints(points)
            viewModel.getDirections()
        })
        //showCity()
        //val repository = DirectionsRepository()
    }

    private fun showPoints(points:ArrayList<GeoPoint>){
        mMap!!.uiSettings.isZoomControlsEnabled = true
        val builder = LatLngBounds.Builder()

        points.forEach {
            val currentPoint = LatLng(it.latitude, it.longitude)

            builder.include(currentPoint)
            mMap!!.addMarker(MarkerOptions().position(currentPoint))
        }

        val bounds = builder.build()
        val padding = 200 // offset from edges of the map in pixels
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)

        mMap!!.moveCamera(cu)
        mMap!!.animateCamera(cu)
    }

    private fun showCity() {
        val cityLatLng = LatLng(40.730610, -73.935242)

        if (markerCity != null)
            markerCity!!.remove()


        markerCity = mMap!!.addMarker(MarkerOptions().position(cityLatLng))

        val cameraPosition = CameraPosition.Builder()
            .target(cityLatLng)      // Sets the center of the map to the selected city
            .zoom(10f)                   // Sets the zoom to view City Level
            .bearing(20f)                // Sets the orientation of the camera to east
            .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
            .build()                   // Creates a CameraPosition from the builder

        mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    // region Lifecycle
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        if(mapView != null)
            mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    //endregion
}
