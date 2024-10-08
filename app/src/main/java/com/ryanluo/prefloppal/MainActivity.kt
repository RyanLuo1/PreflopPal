package com.ryanluo.prefloppal

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
    private lateinit var handStrengthLabel: TextView
    private lateinit var handStrengthBar: ProgressBar
    private lateinit var handStrengthText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupCardSelection()
        setupPositionDropdown()
        setupGetAdviceButton()
        setupBottomNavigation()
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
                displayHandStrength(handStrength)
                showHandStrengthElements()
            } else {
                Toast.makeText(this, "Please select both cards and a position", Toast.LENGTH_SHORT).show()
                hideHandStrengthElements()
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
                else -> ContextCompat.getColor(this, R.color.text_color_primary)
            }
        )
    }

    private fun displayHandStrength(strength: Double) {
        val strengthPercentage = (strength * 10).toInt()
        handStrengthBar.progress = strengthPercentage
        handStrengthText.text = String.format("%.1f / 10.0", strength)
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