package com.ryanluo.prefloppal

import android.os.Bundle
import android.widget.AdapterView
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
    private lateinit var previousActionInput: AutoCompleteTextView
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
        val positions = listOf("UTG", "UTG+1", "UTG+2", "MP", "MP+1", "HJ", "CO", "BTN", "SB", "BB")
        val adapter = ArrayAdapter(this, R.layout.dropdown_item, positions)
        positionDropdown = findViewById(R.id.positionDropdown)
        positionDropdown.setAdapter(adapter)

        positionDropdown.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedPosition = positions[position]
            updatePreviousActionInput(selectedPosition)
        }
    }

    private fun setupPreviousActionInput() {
        previousActionInput = findViewById(R.id.previousActionInput)

        val suggestions = listOf(
            "UTG folds",
            "UTG calls",
            "UTG raises to 2 BB",
            "UTG all in"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
        previousActionInput.setAdapter(adapter)
        previousActionInput.threshold = 1 // Start showing suggestions after 1 character
    }

    private fun updatePreviousActionInput(selectedPosition: String) {
        when (selectedPosition) {
            "UTG" -> {
                previousActionInput.setText("No previous action")
                previousActionInput.isEnabled = false
                previousActionInput.setAdapter(null)
            }
            else -> {
                previousActionInput.setText("")
                previousActionInput.isEnabled = true
                previousActionInput.hint = "Enter previous actions"
                setupPreviousActionInput() // Re-setup suggestions for non-UTG positions
            }
        }
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

    private fun getAdviceAndStrength(card1: String, card2: String, position: String, previousAction: String): Triple<String, String, Double> {
        // TODO: Implement your logic here to generate advice, explanation, and hand strength
        // This is a placeholder implementation
        return Triple("Raise", "You have a strong hand in good position. Previous action: $previousAction", 7.5)
    }

    private fun showAdvicePopup(advice: String, explanation: String, handStrength: Double) {
        val strengthPercentage = (handStrength * 10).toInt()
        val strengthText = String.format("%.1f / 10.0", handStrength)

        val dialog = AdviceDialogFragment.newInstance(advice, explanation, strengthPercentage, strengthText)
        dialog.show(supportFragmentManager, "AdviceDialog")
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