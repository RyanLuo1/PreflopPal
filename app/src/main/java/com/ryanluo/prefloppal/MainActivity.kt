package com.ryanluo.prefloppal

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView

    private lateinit var positionDropdown: AutoCompleteTextView

    private lateinit var getAdviceButton: MaterialButton
    private lateinit var adviceText: TextView

    private lateinit var explanationText: TextView

    private lateinit var handStrengthLabel: TextView
    private lateinit var handStrengthBar: ProgressBar
    private lateinit var handStrengthText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        setupCardSelection()
        setupPositionDropdown()
        setupGetAdviceButton()
        explanationText = findViewById(R.id.explanationText)
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Remove default title
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupCardSelection() {
        card1TextView = findViewById(R.id.card1)
        card2TextView = findViewById(R.id.card2)

        card1TextView.setOnClickListener { showCardSelectionDialog(card1TextView) }
        card2TextView.setOnClickListener { showCardSelectionDialog(card2TextView) }
    }

    private fun showCardSelectionDialog(cardView: TextView) {
        val dialog = CardSelectionDialog()
        dialog.setOnCardSelectedListener { selectedCard ->
            cardView.text = selectedCard
        }
        dialog.show(supportFragmentManager, "CardSelectionDialog")
    }

    private fun setupPositionDropdown() {
        val positions = arrayOf("Button (BTN)", "Small Blind (SB)", "Big Blind (BB)", "Under the Gun (UTG)", "Middle Position (MP)", "Cut-off (CO)")
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, positions)
        positionDropdown = findViewById(R.id.positionDropdown)
        positionDropdown.setAdapter(adapter)

        positionDropdown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedPosition = parent.getItemAtPosition(position) as String
            // Do something with the selected position
        }
    }

    private fun setupGetAdviceButton() {
        getAdviceButton = findViewById(R.id.getAdviceButton)
        adviceText = findViewById(R.id.adviceText)
        handStrengthLabel = findViewById(R.id.handStrengthLabel)
        handStrengthBar = findViewById(R.id.handStrengthBar)
        handStrengthText = findViewById(R.id.handStrengthText)

        getAdviceButton.setOnClickListener {
            val card1 = card1TextView.text.toString()
            val card2 = card2TextView.text.toString()
            val position = positionDropdown.text.toString()

            if (card1.isNotEmpty() && card2.isNotEmpty() && position != "Select Position") {
                val (advice, handStrength) = getAdviceAndStrength(card1, card2, position)
                displayAdvice(advice)
                displayExplanation(advice, card1, card2, position)
                displayHandStrength(handStrength)
                showAdviceElements()
            } else {
                Toast.makeText(this, "Please select both cards and a position", Toast.LENGTH_SHORT).show()
                hideAdviceElements()
            }
        }
    }

    private fun getAdviceAndStrength(card1: String, card2: String, position: String): Pair<String, Double> {
        // Placeholder logic - replace with actual logic later
        val advice = when ((0..2).random()) {
            0 -> "Fold"
            1 -> "Call"
            else -> "Raise"
        }
        val handStrength = (1..100).random() / 10.0
        return Pair(advice, handStrength)
    }

    private fun displayAdvice(advice: String) {
        adviceText.text = advice
        adviceText.setTextColor(
            when (advice) {
                "Fold" -> ContextCompat.getColor(this, R.color.red)
                "Call" -> ContextCompat.getColor(this, R.color.blue_500)
                "Raise" -> ContextCompat.getColor(this, R.color.green_500)
                else -> ContextCompat.getColor(this, android.R.color.white)
            }
        )
    }

    private fun displayExplanation(advice: String, card1: String, card2: String, position: String) {
        val explanation = when (advice) {
            "Fold" -> "With $card1 $card2 in $position, your hand is weak compared to the likely hands of your opponents. It's best to fold and wait for a better opportunity."
            "Call" -> "Your $card1 $card2 in $position has potential, but it's not strong enough to raise. Calling allows you to see the flop cheaply and potentially improve your hand."
            "Raise" -> "Holding $card1 $card2 in $position gives you a strong hand that's likely to be ahead of your opponents' ranges. Raising here will help build the pot and potentially force weaker hands to fold."
            else -> "Based on your $card1 $card2 in $position, this is the recommended action. Consider the specific game context and adjust accordingly."
        }
        explanationText.text = explanation
    }

    private fun showAdviceElements() {
        adviceText.visibility = View.VISIBLE
        explanationText.visibility = View.VISIBLE
        handStrengthLabel.visibility = View.VISIBLE
        handStrengthBar.visibility = View.VISIBLE
        handStrengthText.visibility = View.VISIBLE
    }

    private fun hideAdviceElements() {
        adviceText.visibility = View.GONE
        explanationText.visibility = View.GONE
        handStrengthLabel.visibility = View.GONE
        handStrengthBar.visibility = View.GONE
        handStrengthText.visibility = View.GONE
    }

    private fun displayHandStrength(strength: Double) {
        val strengthPercentage = (strength * 10).toInt()
        handStrengthBar.progress = strengthPercentage
        handStrengthText.text = String.format("%.1f / 10.0", strength)

        // Keep the text color black
        handStrengthText.setTextColor(ContextCompat.getColor(this, android.R.color.white))

        // Ensure the progress bar is visible
        handStrengthBar.visibility = View.VISIBLE
    }

    private fun showHandStrengthElements() {
        handStrengthLabel.visibility = View.VISIBLE
        handStrengthBar.visibility = View.VISIBLE
        handStrengthText.visibility = View.VISIBLE
    }

    private fun hideHandStrengthElements() {
        handStrengthLabel.visibility = View.GONE
        handStrengthBar.visibility = View.GONE
        handStrengthText.visibility = View.GONE
    }

    private fun setupBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    // Handle home navigation
                    true
                }
                R.id.navigation_learn -> {
                    // Handle learn navigation
                    true
                }
                R.id.navigation_history -> {
                    // Handle history navigation
                    true
                }
                else -> false
            }
        }
    }
}