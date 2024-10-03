package com.ryanluo.prefloppal

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton

class CardSelectionDialog : DialogFragment() {

    private var selectedRank: String? = null
    private var selectedSuit: String? = null
    private var onCardSelected: ((String) -> Unit)? = null

    private val ranks = listOf("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2")
    private val suits = listOf(
        "♠" to android.R.color.black,
        "♥" to android.R.color.holo_red_dark,
        "♦" to android.R.color.holo_red_dark,
        "♣" to android.R.color.black
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.CustomDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_card_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rankGrid: GridLayout = view.findViewById(R.id.rankGrid)
        val suitLayout: LinearLayout = view.findViewById(R.id.suitLayout)
        val confirmButton: Button = view.findViewById(R.id.confirmButton)

        setupRankButtons(rankGrid)
        setupSuitButtons(suitLayout)
        setupConfirmButton(confirmButton)
    }

    private fun setupRankButtons(rankGrid: GridLayout) {
        ranks.forEach { rank ->
            val button = MaterialButton(requireContext()).apply {
                text = rank
                textSize = 18f
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                    setMargins(4, 4, 4, 4)
                }
                setTextColor(ContextCompat.getColor(context, android.R.color.black))
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
                setOnClickListener {
                    selectedRank = rank
                    updateButtonStates(rankGrid, this)
                }
            }
            rankGrid.addView(button)
        }
    }

    private fun setupSuitButtons(suitLayout: LinearLayout) {
        suits.forEach { (suit, color) ->
            val button = MaterialButton(requireContext()).apply {
                text = suit
                textSize = 24f
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f).apply {
                    setMargins(4, 0, 4, 0)
                }
                setTextColor(ContextCompat.getColor(context, color))
                setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
                setOnClickListener {
                    selectedSuit = suit
                    updateButtonStates(suitLayout, this)
                }
            }
            suitLayout.addView(button)
        }
    }

    private fun setupConfirmButton(confirmButton: Button) {
        confirmButton.setOnClickListener {
            if (selectedRank != null && selectedSuit != null) {
                onCardSelected?.invoke("$selectedRank$selectedSuit")
                dismiss()
            }
        }
    }

    private fun updateButtonStates(layout: ViewGroup, selectedButton: Button) {
        for (i in 0 until layout.childCount) {
            val button = layout.getChildAt(i) as? Button
            button?.setBackgroundColor(
                if (button == selectedButton) ContextCompat.getColor(requireContext(), R.color.blue_500)
                else ContextCompat.getColor(requireContext(), android.R.color.white)
            )
            button?.setTextColor(
                if (button == selectedButton) ContextCompat.getColor(requireContext(), android.R.color.white)
                else {
                    if (layout is LinearLayout) ContextCompat.getColor(requireContext(), suits[i].second)
                    else ContextCompat.getColor(requireContext(), android.R.color.black)
                }
            )
        }
        view?.findViewById<Button>(R.id.confirmButton)?.isEnabled = selectedRank != null && selectedSuit != null
    }

    fun setOnCardSelectedListener(listener: (String) -> Unit) {
        onCardSelected = listener
    }
}