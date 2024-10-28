package com.ryanluo.prefloppal.main

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.ryanluo.prefloppal.dialogs.AdviceDialogFragment
import com.ryanluo.prefloppal.utils.Card
import com.ryanluo.prefloppal.dialogs.CardSelectionDialog
import com.ryanluo.prefloppal.utils.Hand
import com.ryanluo.prefloppal.utils.PokerLogic
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.data.HandRecord
import com.ryanluo.prefloppal.utils.FirebaseManager

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView
    private lateinit var positionDropdown: AutoCompleteTextView
    private lateinit var previousActionInput: AutoCompleteTextView
    private lateinit var getAdviceButton: MaterialButton
    private lateinit var firebaseManager: FirebaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseManager = FirebaseManager.getInstance()

        setupToolbar()
        setupCardSelection()
        setupPositionDropdown()
        setupPositionInfo()
        setupPreviousActionInput()
        setupGetAdviceButton()
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        //findViewById<TextView>(R.id.toolbarTitle).text = "PreFlop Pal"
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
        // Get the currently selected cards
        val selectedCards = setOf(card1TextView.text.toString(), card2TextView.text.toString())

        // Remove the empty string if a card hasn't been selected yet
        val unavailableCards = selectedCards.filter { it.isNotEmpty() }.toSet()

        dialog.setUnavailableCards(unavailableCards)
        dialog.show(supportFragmentManager, "CardSelectionDialog")
    }

    private fun setupPositionDropdown() {
        val positions = listOf("UTG", "MP", "CO", "BTN", "SB" , "BB")
        val adapter = ArrayAdapter(this, R.layout.position_dropdown, positions)
        positionDropdown = findViewById(R.id.positionDropdown)
        positionDropdown.setAdapter(adapter)

        positionDropdown.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedPosition = positions[position]
            updatePreviousActionInput(selectedPosition)
        }
    }

    private fun setupPositionInfo() {
        val infoIcon = findViewById<ImageView>(R.id.positionInfoIcon)
        infoIcon.setOnClickListener {
            showPositionInfoDialog()
        }
    }

    private fun showPositionInfoDialog() {
        val dialog = Dialog(this, R.style.DialogTheme)
        dialog.setContentView(R.layout.position_info_image)

        // Get screen dimensions
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val dialogWidth = (screenWidth * 0.90).toInt()

        dialog.window?.apply {
            setLayout(dialogWidth, WindowManager.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            // Set the dialog position
            attributes?.apply {
                gravity = Gravity.CENTER  // This centers the dialog
                windowAnimations = 0  // Remove default animations
            }

            // Optional dim background
            addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setDimAmount(0.5f)
        }

        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun setupPreviousActionInput() {
        previousActionInput = findViewById(R.id.previousActionInput)
    }

    private fun updatePreviousActionInput(selectedPosition: String) {
        when (selectedPosition) {
            "UTG" -> {
                val suggestions = arrayOf("No action")
                val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
                previousActionInput.setAdapter(adapter)
                previousActionInput.setText("") // Clear any existing text
                previousActionInput.isEnabled = true
            }
            else -> {
                val suggestions = arrayOf(
                    "UTG folds",
                    "UTG raises to 2.5 BB",
                    "UTG all in"
                )
                val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions)
                previousActionInput.setAdapter(adapter)
                previousActionInput.setText("") // Clear any existing text
                previousActionInput.isEnabled = true
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

            if (card1.isNotEmpty() && card2.isNotEmpty() && position != "Select Position" && position != "") {
                val (advice, explanation, handStrength) = getAdviceAndStrength(card1, card2, position, previousAction)

                // Create and save the hand record
                val handRecord = HandRecord(
                    card1 = card1,
                    card2 = card2,
                    position = position,
                    previousAction = previousAction,
                    advice = advice,
                    timestamp = System.currentTimeMillis()
                )

                // Save to Firebase
                firebaseManager.saveHandRecord(handRecord) { success ->
                    if (!success) {
                        runOnUiThread {
                            Toast.makeText(this, "Failed to save hand record", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                // Show advice popup
                showAdvicePopup(advice, explanation, handStrength)
            } else {
                val missingItems = mutableListOf<String>()
                if (card1.isEmpty() || card2.isEmpty()) missingItems.add("cards")
                if (position == "Select Position" || position == "") missingItems.add("position")

                val missingItemsText = missingItems.joinToString(", ")
                Toast.makeText(this, "Please select $missingItemsText before getting advice", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun getAdviceAndStrength(card1: String, card2: String, position: String, previousAction: String): Triple<String, String, Double> {
        val hand = Hand(Card(card1[0], card1[1]), Card(card2[0], card2[1]))
        return PokerLogic.getAdvice(hand, position, previousAction)
    }

    private fun showAdvicePopup(advice: String, explanation: String, handStrength: Double) {
        val strengthPercentage = (handStrength * 10).toInt()
        val strengthText = String.format("%.1f / 10.0", handStrength)

        val dialog =
            AdviceDialogFragment.newInstance(advice, explanation, strengthPercentage, strengthText)
        dialog.show(supportFragmentManager, "AdviceDialog")
    }

    private fun setupBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    // Already on home screen
                    true
                }
                R.id.navigation_learn -> {
                    // Handle learn navigation
                    true
                }
                R.id.navigation_history -> {
                    // Handle history navigation
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}