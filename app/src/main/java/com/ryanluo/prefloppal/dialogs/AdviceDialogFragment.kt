package com.ryanluo.prefloppal.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ryanluo.prefloppal.R

class AdviceDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.advice_popup, null)

        val adviceText = view.findViewById<TextView>(R.id.adviceText)
        val explanationText = view.findViewById<TextView>(R.id.explanationText)
        val handStrengthBar = view.findViewById<ProgressBar>(R.id.handStrengthBar)
        val handStrengthText = view.findViewById<TextView>(R.id.handStrengthText)

        arguments?.let { args ->
            val advice = args.getString("advice", "")
            adviceText.text = advice
            explanationText.text = args.getString("explanation", "")
            handStrengthBar.progress = args.getInt("strength", 0)
            handStrengthText.text = args.getString("strengthText", "")

            // Set color based on advice
            val adviceColor = getColorForAdvice(advice)
            adviceText.setTextColor(adviceColor)
        }

        return MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setView(view)
            .create()
    }

    private fun getColorForAdvice(advice: String): Int {
        val colorRes = when {
            advice.contains("4-Bet for Value", ignoreCase = true) -> R.color.green
            advice.contains("4-Bet as a Bluff or Fold", ignoreCase = true) -> R.color.orange
            advice.contains("3-Bet for Value", ignoreCase = true) -> R.color.green
            advice.contains("3-Bet as a Bluff or Fold", ignoreCase = true) -> R.color.orange
            advice.contains("raise", ignoreCase = true) -> R.color.green_500
            advice.contains("call", ignoreCase = true) -> R.color.blue_500
            advice.contains("fold", ignoreCase = true) -> R.color.red
            else -> android.R.color.white
        }
        return ContextCompat.getColor(requireContext(), colorRes)
    }

    companion object {
        fun newInstance(advice: String, explanation: String, strength: Int, strengthText: String): AdviceDialogFragment {
            return AdviceDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("advice", advice)
                    putString("explanation", explanation)
                    putInt("strength", strength)
                    putString("strengthText", strengthText)
                }
            }
        }
    }
}