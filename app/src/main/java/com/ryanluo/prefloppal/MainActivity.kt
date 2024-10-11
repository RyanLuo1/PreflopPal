package com.ryanluo.prefloppal

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView
    private lateinit var positionDropdown: AutoCompleteTextView
    private lateinit var previousActionInput: TextView
    private lateinit var getAdviceButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupCardSelection()
        setupPositionDropdown()
        setupPreviousActionInput()
        setupGetAdviceButton()
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        findViewById<TextView>(R.id.toolbarTitle).text = "PreFlop Pal"
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
    }

    private fun setupPreviousActionInput() {
        previousActionInput = findViewById(R.id.previousActionInput)

        val suggestions = listOf(
            "UTG raised to 3BB",
            "MP called"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
        (previousActionInput as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun setupGetAdviceButton() {
        getAdviceButton = findViewById(R.id.getAdviceButton)

        getAdviceButton.setOnClickListener {
            val card1 = card1TextView.text.toString()
            val card2 = card2TextView.text.toString()
            val position = positionDropdown.text.toString()
            val previousAction = previousActionInput.text.toString()

            if (card1.isNotEmpty() && card2.isNotEmpty() && position != "Select Position") {
                val (advice, explanation, handStrength) = getAdviceAndStrength(card1, card2, position, previousAction)
                showAdvicePopup(advice, explanation, handStrength)
            } else {
                Toast.makeText(this, "Please select both cards, a position, and describe any previous action", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showAdvicePopup(advice: String, explanation: String, handStrength: Double) {
        val strengthPercentage = (handStrength * 10).toInt()
        val strengthText = String.format("%.1f / 10.0", handStrength)

        val dialog = AdviceDialogFragment.newInstance(advice, explanation, strengthPercentage, strengthText)
        dialog.show(supportFragmentManager, "AdviceDialog")
    }

    private fun getAdviceAndStrength(card1: String, card2: String, position: String, previousAction: String): Triple<String, String, Double> {
        // Implement your logic here to generate advice, explanation, and hand strength
        // This is a placeholder implementation
        return Triple("Raise", "You have a strong hand in good position. $previousAction", 7.5)
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