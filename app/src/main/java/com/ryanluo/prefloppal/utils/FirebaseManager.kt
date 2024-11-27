package com.ryanluo.prefloppal.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ryanluo.prefloppal.data.HandRecord
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

    // In FirebaseManager.kt, add these methods:
    fun getHandHistory(callback: (List<HandRecord>) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        database.child("users").child(userId).child("handHistory")
            .orderByChild("timestamp")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val handRecords = mutableListOf<HandRecord>()
                    for (childSnapshot in snapshot.children) {
                        childSnapshot.getValue(HandRecord::class.java)?.let {
                            handRecords.add(it)
                        }
                    }
                    callback(handRecords.reversed()) // Most recent first
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(emptyList())
                }
            })
    }

    fun saveHandRecord(handRecord: HandRecord, callback: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        val handKey = database.child("users").child(userId).child("handHistory").push().key

        handKey?.let {
            handRecord.id = it  // Make sure your HandRecord class has a mutable id property
            database.child("users").child(userId).child("handHistory").child(it)
                .setValue(handRecord)
                .addOnCompleteListener { task ->
                    callback(task.isSuccessful)
                }
        } ?: callback(false)
    }

    fun deleteHandRecord(handId: String, callback: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return
        database.child("users").child(userId).child("handHistory").child(handId)
            .removeValue()
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    fun sendEmailVerification(callback: (Boolean, String?) -> Unit) {
        auth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun isEmailVerified(): Boolean {
        return auth.currentUser?.isEmailVerified ?: false
    }

}