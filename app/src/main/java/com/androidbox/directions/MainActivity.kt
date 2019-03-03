package com.androidbox.directions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.androidbox.directions.repository.DirectionsRepository
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Choose an arbitrary request code value
    private val auth = FirebaseAuth.getInstance()
    private val authUI  = AuthUI.getInstance()
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(my_toolbar)

        if (auth.currentUser != null) {
            // already signed in
            showUserEmail()
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

        // Create and launch sign-in intent
        startActivityForResult(
            authUI
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            , RC_SIGN_IN)
    }

    // After the Sign in we can get data here
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        showUserEmail()

    }

    private fun showUserEmail() {
        val repository = DirectionsRepository()

        repository.updateSchedule(auth.currentUser!!.email!!)
        textView.text = auth.currentUser!!.email
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_signout -> {
                authUI.signOut(this).addOnCompleteListener{
                    signIn()
                }
            }
        }
        return true
    }
}
