package com.ryanluo.prefloppal.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ryanluo.prefloppal.data.User

class FirebaseManager private constructor() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    companion object {
        @Volatile
        private var instance: FirebaseManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FirebaseManager().also { instance = it }
        }
    }

    fun createUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Create user profile in database
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                    val user = User(
                        email = email,
                        uid = userId
                    )
                    database.child("users").child(userId).setValue(user)
                        .addOnCompleteListener { dbTask ->
                            callback(dbTask.isSuccessful, if (dbTask.isSuccessful) null else dbTask.exception?.message)
                        }
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign-in was successful
                    callback(true, null)
                } else {
                    // Use a generic error message for security reasons
                    callback(false, "Incorrect email or password.")
                }
            }
    }


    fun signOut() {
        auth.signOut()
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }
}