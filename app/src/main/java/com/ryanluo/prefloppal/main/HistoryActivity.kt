package com.ryanluo.prefloppal.main

import android.app.Dialog
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.adapters.HandHistoryAdapter
import com.ryanluo.prefloppal.data.HandRecord
import com.ryanluo.prefloppal.utils.FirebaseManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var firebaseManager: FirebaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)

        // Initialize Firebase manager
        firebaseManager = FirebaseManager.getInstance()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.historyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Load hand history
        loadHandHistory()

        setupSwipeToDelete()

        // Set up bottom navigation
        setupBottomNavigation()
    }

    private fun loadHandHistory() {
        firebaseManager.getHandHistory { handRecords ->
            recyclerView.adapter = HandHistoryAdapter(ArrayList(handRecords)) { record ->
                showHandDetails(record)
            }
        }
    }

    private fun showHandDetails(record: HandRecord) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_hand_history_details)

        dialog.window?.apply {
            setLayout(
                (resources.displayMetrics.widthPixels * 0.9).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog.findViewById<TextView>(R.id.handTextView).text = "${record.card1} ${record.card2}"
        dialog.findViewById<TextView>(R.id.tableSizeTextView).text = buildBoldText("Table Size: ", record.tableSize)
        dialog.findViewById<TextView>(R.id.positionTextView).text = buildBoldText("Position: ", record.position)
        dialog.findViewById<TextView>(R.id.previousActionTextView).text = buildBoldText("Previous Action: ", record.previousAction)
        dialog.findViewById<TextView>(R.id.adviceTextView).text = buildBoldText("Advice: ", record.advice)
        dialog.findViewById<TextView>(R.id.timestampTextView).text = SimpleDateFormat(
            "MMM dd, yyyy HH:mm:ss",
            Locale.getDefault()
        ).format(Date(record.timestamp))

        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }

    // Helper function to create bold text for label
    private fun buildBoldText(label: String, value: String): SpannableString {
        val spannable = SpannableString("$label$value")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD), // Bold style
            0,
            label.length, // Apply only to the label part
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }


    private fun setupSwipeToDelete() {
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(
            0,  // Drag directions (none)
            ItemTouchHelper.LEFT  // Only allow left swipe
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val adapter = recyclerView.adapter as HandHistoryAdapter
                val record = adapter.getItem(position)

                // Create a custom TextView for the title
                val title = TextView(this@HistoryActivity).apply {
                    text = "Delete Hand History"
                    textSize = 20f
                    setTypeface(typeface, Typeface.BOLD)  // Make text bold
                    setPadding(16, 16, 16, 16)
                    gravity = Gravity.CENTER
                }

                val alertDialog = AlertDialog.Builder(this@HistoryActivity)
                    .setCustomTitle(title)
                    .setMessage("Are you sure you want to delete this hand history?")
                    .setPositiveButton("Delete") { _, _ ->
                        firebaseManager.deleteHandRecord(record.id) { success ->
                            runOnUiThread {
                                if (success) {
                                    adapter.removeItem(position)
                                    Toast.makeText(
                                        this@HistoryActivity,
                                        "Hand record deleted",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    adapter.notifyItemChanged(position)
                                    Toast.makeText(
                                        this@HistoryActivity,
                                        "Failed to delete hand record",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        adapter.notifyItemChanged(position)
                    }
                    .setOnCancelListener {
                        adapter.notifyItemChanged(position)
                    }
                    .create()

                // Apply the custom rounded background to the dialog window
                alertDialog.window?.setBackgroundDrawableResource(R.drawable.card_background)

                // Show the dialog
                alertDialog.show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                // Only show if swiping left (dX will be negative)
                if (dX < 0) {
                    val itemView = viewHolder.itemView
                    val deleteIcon = ContextCompat.getDrawable(
                        this@HistoryActivity,
                        R.drawable.ic_delete
                    )
                    val background = ColorDrawable(Color.RED)

                    val iconMargin = (itemView.height - deleteIcon!!.intrinsicHeight) / 2
                    val iconTop = itemView.top + iconMargin
                    val iconBottom = iconTop + deleteIcon.intrinsicHeight

                    val iconRight = itemView.right - iconMargin
                    val iconLeft = iconRight - deleteIcon.intrinsicWidth
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom)

                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )

                    background.draw(c)
                    deleteIcon.draw(c)
                }

                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(recyclerView)
    }


    // HistoryActivity (Left Screen)
    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.navigation_history

            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_history -> {
                        true // Already here
                    }
                    R.id.navigation_home -> {
                        startActivity(Intent(this@HistoryActivity, MainActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                        true
                    }
                    R.id.navigation_learn -> {
                        startActivity(Intent(this@HistoryActivity, LearnActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                        true
                    }
                    else -> false
                }
            }
        }
    }
}