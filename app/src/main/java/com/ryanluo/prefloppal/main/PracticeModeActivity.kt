package com.ryanluo.prefloppal.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.ryanluo.prefloppal.R

class PracticeModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practice_mode_activity)

        setupToolbar()
        setupButtons()
    }

    private fun setupToolbar() {
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }
    }

    private fun setupButtons() {
        val feedbackCard = findViewById<CardView>(R.id.feedbackCard)

        // Setup click listeners for all action buttons
        findViewById<MaterialButton>(R.id.foldButton).setOnClickListener {
            showFeedback()
        }

        findViewById<MaterialButton>(R.id.callButton).setOnClickListener {
            showFeedback()
        }

        findViewById<MaterialButton>(R.id.raiseButton).setOnClickListener {
            showFeedback()
        }

        findViewById<MaterialButton>(R.id.allinButton).setOnClickListener {
            showFeedback()
        }

        // Setup next hand button
        findViewById<MaterialButton>(R.id.nextHandButton).setOnClickListener {
            feedbackCard.visibility = View.GONE
            // Generate new hand here
        }
    }

    private fun showFeedback() {
        val feedbackCard = findViewById<CardView>(R.id.feedbackCard)
        feedbackCard.visibility = View.VISIBLE
    }





}