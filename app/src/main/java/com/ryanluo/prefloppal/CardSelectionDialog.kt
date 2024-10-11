package com.ryanluo.prefloppal

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.ryanluo.prefloppal.R


class CardSelectionDialog : DialogFragment() {
    private lateinit var onCardSelectedListener: (String) -> Unit
    private var unavailableCards: Set<String> = emptySet()
    private var selectedRank: String? = null
    private var selectedSuit: String? = null

    private val ranks = listOf("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2")
    private val suits = listOf("♠", "♥", "♦", "♣")

    fun setOnCardSelectedListener(listener: (String) -> Unit) {
        onCardSelectedListener = listener
    }

    fun setUnavailableCards(cards: Set<String>) {
        unavailableCards = cards
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_card_selection, null)

        val rankGrid: GridLayout = view.findViewById(R.id.rankGrid)
        val suitLayout: LinearLayout = view.findViewById(R.id.suitLayout)
        val confirmButton: Button = view.findViewById(R.id.confirmButton)

        setupRankButtons(rankGrid)
        setupSuitButtons(suitLayout)

        confirmButton.setOnClickListener {
            if (selectedRank != null && selectedSuit != null) {
                val selectedCard = "$selectedRank$selectedSuit"
                if (selectedCard !in unavailableCards) {
                    onCardSelectedListener(selectedCard)
                    dismiss()
                }
            }
        }

        builder.setView(view)
        return builder.create()
    }

    private fun setupRankButtons(rankGrid: GridLayout) {
        ranks.forEach { rank ->
            val button = createButton(rank).apply {
                isEnabled = suits.any { suit -> "$rank$suit" !in unavailableCards }
                setOnClickListener {
                    selectedRank = rank
                    updateButtonStates(rankGrid, this)
                }
            }
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(4, 4, 4, 4)
            }
            rankGrid.addView(button, params)
        }
    }

    private fun setupSuitButtons(suitLayout: LinearLayout) {
        suits.forEach { suit ->
            val button = createButton(suit).apply {
                isEnabled = ranks.any { rank -> "$rank$suit" !in unavailableCards }
                setOnClickListener {
                    selectedSuit = suit
                    updateButtonStates(suitLayout, this)
                }
            }
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            params.setMargins(4, 4, 4, 4)
            suitLayout.addView(button, params)
        }
    }

    private fun createButton(text: String): Button {
        return Button(requireContext()).apply {
            this.text = text
            setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_gray_300))
            setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
            textSize = 20f
            setTypeface(null, Typeface.BOLD)
        }
    }

    private fun updateButtonStates(layout: ViewGroup, selectedButton: Button) {
        for (i in 0 until layout.childCount) {
            (layout.getChildAt(i) as? Button)?.let { button ->
                if (button == selectedButton) {
                    button.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                    button.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                } else {
                    button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_gray_800))
                    button.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                }
            }
        }
    }
}