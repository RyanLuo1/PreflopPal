package com.ryanluo.prefloppal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupCardSelection()
        setupPositionSpinner()
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

    private fun setupPositionSpinner() {
        val spinner: Spinner = findViewById(R.id.positionSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.poker_positions,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
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