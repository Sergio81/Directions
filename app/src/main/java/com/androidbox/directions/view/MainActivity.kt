package com.androidbox.directions.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.androidbox.directions.R
import com.androidbox.directions.app.DirectionsApp
import com.androidbox.directions.view.map.MapViewModel
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val authUI = AuthUI.getInstance()
    private val RC_SIGN_IN = 123
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as DirectionsApp).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        setSupportActionBar(my_toolbar)

        if (auth.currentUser != null) {
            // already signed in
            updateUser(true)
        } else {
            // not signed in
            signIn()
        }
    }

    private fun signIn() {
        // Choose authUI providers
        val providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        updateUser(false)

        // Create and launch sign-in intent
        startActivityForResult(
            authUI
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            , RC_SIGN_IN
        )
    }

    // After the Sign in we can get data here
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //showUserEmail()
        updateUser(true)
    }

    private fun updateUser(active: Boolean) {
        if (active)
            viewModel.user.value!!.email = auth.currentUser!!.email!!
        else
            viewModel.user.value!!.email = ""

        viewModel.user.value!!.signedIn = active
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_signout -> {
                authUI.signOut(this).addOnCompleteListener {
                    signIn()
                }
            }
        }
        return true
    }
}
