package com.ryanluo.prefloppal.main

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.adapters.HandHistoryAdapter
import com.ryanluo.prefloppal.data.HandRecord
import com.ryanluo.prefloppal.utils.FirebaseManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var firebaseManager: FirebaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)

        // Initialize Firebase manager
        firebaseManager = FirebaseManager.getInstance()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.historyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up bottom navigation
        setupBottomNavigation()

        // Load hand history
        loadHandHistory()
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Set the History item as selected
        bottomNavigation.selectedItemId = R.id.navigation_history

        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    // Go back to MainActivity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.navigation_learn -> {
                    Toast.makeText(this, "Learn feature coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_history -> {
                    // Already on history screen
                    true
                }
                else -> false
            }
        }
    }

    private fun loadHandHistory() {
        firebaseManager.getHandHistory { handRecords ->
            recyclerView.adapter = HandHistoryAdapter(handRecords) { record ->
                showHandDetails(record)
            }
        }
    }

    private fun showHandDetails(record: HandRecord) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_hand_history_details)

        dialog.window?.apply {
            setLayout(
                (resources.displayMetrics.widthPixels * 0.9).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog.findViewById<TextView>(R.id.handTextView).text = "${record.card1} ${record.card2}"
        dialog.findViewById<TextView>(R.id.positionTextView).text = "Position: ${record.position}"
        dialog.findViewById<TextView>(R.id.previousActionTextView).text = "Previous Action: ${record.previousAction}"
        dialog.findViewById<TextView>(R.id.adviceTextView).text = "Advice: ${record.advice}"
        dialog.findViewById<TextView>(R.id.timestampTextView).text = SimpleDateFormat(
            "MMM dd, yyyy HH:mm:ss",
            Locale.getDefault()
        ).format(Date(record.timestamp))

        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}