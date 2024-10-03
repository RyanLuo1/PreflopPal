package com.ryanluo.prefloppal

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.TextView
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupCardSelection()
        setupPositionDropdown()
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
        val positionDropdown: AutoCompleteTextView = findViewById(R.id.positionDropdown)
        positionDropdown.setAdapter(adapter)

        positionDropdown.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedPosition = parent.getItemAtPosition(position) as String
            // Do something with the selected position
        }
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