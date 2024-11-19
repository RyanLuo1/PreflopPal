package com.ryanluo.prefloppal.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.ryanluo.prefloppal.R

class PracticeModeActivity : AppCompatActivity() {

    enum class Action {
        FOLD,
        CALL,
        RAISE,
        ALL_IN
    }

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
            showFeedback(Action.FOLD)
        }

        findViewById<MaterialButton>(R.id.callButton).setOnClickListener {
            showFeedback(Action.CALL)
        }

        findViewById<MaterialButton>(R.id.raiseButton).setOnClickListener {
            showFeedback(Action.RAISE)
        }

        findViewById<MaterialButton>(R.id.allinButton).setOnClickListener {
            showFeedback(Action.ALL_IN)
        }

        // Setup next hand button
        findViewById<MaterialButton>(R.id.nextHandButton).setOnClickListener {
            feedbackCard.visibility = View.GONE
            // Generate new hand here
        }
    }

    private fun showFeedback(selectedAction: Action) {
        val feedbackCard = findViewById<CardView>(R.id.feedbackCard)
        val resultText = findViewById<TextView>(R.id.resultText)
        val explanationText = findViewById<TextView>(R.id.explanationText)

        // Raise is the correct option for our placeholder scenario
        val isCorrect = selectedAction == Action.RAISE

        // Update result text and color
        resultText.text = if (isCorrect) "Correct!" else "Incorrect"
        resultText.setTextColor(
            ContextCompat.getColor(
                this,
                if (isCorrect) R.color.green else R.color.red
            )
        )

        // Set explanation based on the scenario
        explanationText.text = when(selectedAction) {
            Action.FOLD -> "Folding AK suited after UTG raises and CO 3-bets is too nitty. " +
                    "This is a premium hand that performs very well against both players' ranges " +
                    "and deserves a 4-bet."

            Action.CALL -> "While calling might seem safe, with AK suited we should be 4-betting. " +
                    "Calling lets weaker hands see a flop cheaply and doesn't maximize value from our premium holding."

            Action.RAISE -> "4-betting with AK suited is optimal here. We have a premium hand that " +
                    "performs well against both UTG's RFI range and CO's 3-betting range. We can " +
                    "comfortably get it in against most 5-bet jams."

            Action.ALL_IN -> "While 4-betting is correct, moving all-in is an overplay. " +
                    "A standard 4-bet sizing gives us better options and doesn't risk our whole stack " +
                    "when a smaller raise would achieve the same goal."
        }

        feedbackCard.visibility = View.VISIBLE
    }
}