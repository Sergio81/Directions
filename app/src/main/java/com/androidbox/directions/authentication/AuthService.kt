package com.androidbox.directions.authentication

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.util.*

//class AuthService {
//    private val auth = FirebaseAuth.getInstance()
//    private val RC_SIGN_IN = 123
//
//    var currentUser:String = ""
//
//    private fun signIn() {
//        // Choose authUI providers
//        val providers = Arrays.asList(
//            AuthUI.IdpConfig.EmailBuilder().build()
//        )
//
//        // Create and launch sign-in intent
////        startActivityForResult(
////            authUI
////                .createSignInIntentBuilder()
////                .setAvailableProviders(providers)
////                .build()
////            , RC_SIGN_IN)
//    }
//}