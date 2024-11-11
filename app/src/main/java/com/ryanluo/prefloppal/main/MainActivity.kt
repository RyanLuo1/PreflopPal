package com.ryanluo.prefloppal.main

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListPopupWindow
import android.widget.PopupMenu
import android.widget.Space
import android.widget.TableLayout
import android.widget.TableRow
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
import com.ryanluo.prefloppal.utils.TableSize

class MainActivity : AppCompatActivity() {
    private lateinit var card1TextView: TextView
    private lateinit var card2TextView: TextView
    private lateinit var positionDropdown: AutoCompleteTextView
    private lateinit var previousActionInput: AutoCompleteTextView
    private lateinit var getAdviceButton: MaterialButton
    private lateinit var firebaseManager: FirebaseManager

    private lateinit var actionTable: TableLayout
    private lateinit var actionSummaryText: TextView

    private var firstRaisePosition: String? = null
    private var first3BetPosition: String? = null
    private var first4BetPosition: String? = null

    private val positionActions = mutableMapOf<String, String>()
    private var currentTableSize = "9 Players"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        firebaseManager = FirebaseManager.getInstance()

        setupToolbar()
        setupCardSelection()
        setupPositionDropdown()
        setupPositionInfo()

        setupActionTable()

        setupPreviousActionInfo()
        setupGetAdviceButton()
        setupBottomNavigation()
    }

    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Setup menu icon
        findViewById<ImageView>(R.id.menuIcon).setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun showPopupMenu(view: View) {
        PopupMenu(this, view).apply {
            menuInflater.inflate(R.menu.home_menu, menu)

            // Set current check state
            when (currentTableSize) {
                "6 Players" -> {
                    menu.findItem(R.id.table_size_6).isChecked = true
                    menu.findItem(R.id.table_size_9).isChecked = false
                }
                "9 Players" -> {
                    menu.findItem(R.id.table_size_9).isChecked = true
                    menu.findItem(R.id.table_size_6).isChecked = false
                }
            }

            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.table_size_6 -> {
                        updateTableSize("6 Players")
                        menu.findItem(R.id.table_size_6).isChecked = true
                        menu.findItem(R.id.table_size_9).isChecked = false
                        true
                    }
                    R.id.table_size_9 -> {
                        updateTableSize("9 Players")
                        menu.findItem(R.id.table_size_9).isChecked = true
                        menu.findItem(R.id.table_size_6).isChecked = false
                        true
                    }
                    else -> false
                }
            }
            show()
        }
    }

    private fun updateTableSize(size: String) {
        currentTableSize = size
        setupPositionDropdown()
        positionDropdown.setText("")
        findViewById<View>(R.id.previousActionContainer).visibility = View.GONE
        setupActionTable()
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
        positionDropdown = findViewById(R.id.positionDropdown)

        val positions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val adapter = ArrayAdapter(this, R.layout.position_dropdown, positions)
        positionDropdown.setAdapter(adapter)

        positionDropdown.setOnItemClickListener { _, _, _, _ ->
            val selectedPosition = positionDropdown.text.toString()
            // Make the container visible
            findViewById<View>(R.id.previousActionContainer).visibility = View.VISIBLE
            updateActionTableForPosition(selectedPosition)
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

    private fun setupActionTable() {
        // Initialize the TableLayout
        actionTable = findViewById(R.id.actionTable)
        actionSummaryText = findViewById(R.id.actionSummaryText)

        // Set initial properties
        actionTable.apply {
            // Remove any existing rows
            removeAllViews()

            // Set column weights/stretching
            isStretchAllColumns = false

            // Add some padding around the table
            setPadding(16, 8, 16, 8)
        }

        // Initially hide the table until a position is selected
        actionTable.visibility = View.GONE
        actionSummaryText.visibility = View.GONE
    }








    private fun updateActionTableForPosition(selectedPosition: String) {
        // Clear existing table and actions
        actionTable.removeAllViews()
        positionActions.clear()
        resetBettingStates()

        // Get all positions up to selected position
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val relevantPositions = allPositions.takeWhile { it != selectedPosition } + selectedPosition

        // Add rows for each relevant position
        relevantPositions.forEach { position ->
            addPositionRow(position, position == selectedPosition)
        }

        // Make table visible
        actionTable.visibility = View.VISIBLE
    }

    private fun updateTableAfterUserAction(userPosition: String, userAction: String) {
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        // Determine which positions need to show up
        val positionsToAdd = mutableListOf<String>()

        when (userAction) {
            "3bet" -> {
                // After 3bet, show positions after user and original raiser
                val userIndex = allPositions.indexOf(userPosition)
                if (userIndex != -1) {
                    positionsToAdd.addAll(allPositions.subList(userIndex + 1, allPositions.size))
                }
                firstRaisePosition?.let { raisePos ->
                    positionsToAdd.add(raisePos)
                }
            }
            "4bet" -> {
                // After 4bet, ONLY show the 3bettor with fold/all-in
                first3BetPosition?.let { threeBetPos ->
                    positionsToAdd.add(threeBetPos)
                }
            }
        }

        // Add the new positions to the table
        positionsToAdd.forEach { position ->
            when (userAction) {
                "3bet" -> {
                    if (position == firstRaisePosition) {
                        addPositionRowWithOptions(position, listOf("fold", "call", "4bet"))
                    } else {
                        addPositionRow(position, false)
                    }
                }
                "4bet" -> {
                    addPositionRowWithOptions(position, listOf("fold", "all in"))
                }
                else -> {
                    addPositionRow(position, false)
                }
            }
        }
    }

    private fun addPositionRow(position: String, isSelectedPosition: Boolean) {
        val row = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            setPadding(8, 8, 8, 8)
        }

        // Position name
        TextView(this).apply {
            text = position
            textSize = 16f
            setPadding(16, 8, 16, 8)
            setTextColor(getColor(R.color.dark_purple))
        }.also { row.addView(it) }

        // Add action buttons
        addActionButtons(row, position, isSelectedPosition)

        actionTable.addView(row)

        // Pre-select appropriate action
        if (!isSelectedPosition) {
            if (position == firstRaisePosition && positionActions[position] == "raise") {
                // If this is UTG's original row, highlight raise
                val buttonsLayout = row.getChildAt(1) as LinearLayout
                val raiseButton = buttonsLayout.getChildAt(1) as MaterialButton  // Second button should be raise
                updateButtonAppearance(raiseButton, true)
                positionActions[position] = "raise"
            } else {
                positionActions[position] = "fold"
                updateActionSummary()
            }
        }
    }

    // Add this helper function
    private fun addPositionRowWithOptions(position: String, buttonOptions: List<String>) {

        val row = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            setPadding(8, 8, 8, 8)
        }

        // Position name
        TextView(this).apply {
            text = position
            textSize = 16f
            setPadding(16, 8, 16, 8)
            setTextColor(getColor(R.color.dark_purple))
        }.also { row.addView(it) }

        // Add buttons layout
        val buttonsLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = TableRow.LayoutParams(  // Changed to TableRow.LayoutParams
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_VERTICAL
                marginStart = 16
            }
        }

        buttonOptions.forEach { buttonAction ->
            val button = createActionButton(buttonAction, position, false)
            buttonsLayout.addView(button)
        }

        row.addView(buttonsLayout)
        actionTable.addView(row)
    }

    private fun addActionButtons(row: TableRow, position: String, isSelectedPosition: Boolean) {
        val buttonsLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = TableRow.LayoutParams(  // Changed to TableRow.LayoutParams
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = Gravity.CENTER_VERTICAL  // Center buttons vertically
                marginStart = 16  // Add some margin
            }
        }

        // Initial buttons are always fold and raise
        val foldButton = createActionButton("fold", position, isSelectedPosition)
        val raiseButton = createActionButton("raise", position, isSelectedPosition)

        // Add buttons to layout
        buttonsLayout.addView(foldButton)
        buttonsLayout.addView(raiseButton)

        row.addView(buttonsLayout)

        // Pre-select fold if it's not the selected position
        if (!isSelectedPosition) {
            foldButton.isSelected = true
            updateButtonAppearance(foldButton, true)
        }
    }

    private fun createActionButton(action: String, position: String, isSelectedPosition: Boolean): MaterialButton {
        return MaterialButton(this, null, R.style.Widget_MaterialComponents_Button_OutlinedButton).apply {
            text = action.capitalize()
            textSize = 14f
            setTextColor(getColor(R.color.dark_purple))
            backgroundTintList = ColorStateList.valueOf(getColor(R.color.white))
            strokeColor = ColorStateList.valueOf(getColor(R.color.primary_purple))
            strokeWidth = 2
            minWidth = 72.dpToPx()  // Set minimum width
            minHeight = 36.dpToPx() // Set minimum height
            insetTop = 0            // Remove internal padding
            insetBottom = 0         // Remove internal padding
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginStart = 8
                marginEnd = 8
            }
            setPadding(12.dpToPx(), 0, 12.dpToPx(), 0)  // Add horizontal padding

            setOnClickListener {
                handleActionClick(this, position, action)
            }
        }
    }

    private fun handleActionClick(clickedButton: MaterialButton, position: String, action: String) {
        // Get parent layout and all buttons
        val buttonsLayout = clickedButton.parent as LinearLayout
        val buttons = (0 until buttonsLayout.childCount)
            .map { buttonsLayout.getChildAt(it) as MaterialButton }

        // Store previous action before updating
        val previousAction = positionActions[position]

        // Immediately highlight the clicked button
        buttons.forEach { button ->
            updateButtonAppearance(button, button == clickedButton)
        }

        // Update action in map immediately
        positionActions[position] = action

        // Check if this is a de-escalation
        val isDeEscalation = when {
            previousAction == "4bet" && (action == "3bet" || action == "call" || action == "fold") -> true
            previousAction == "3bet" && (action == "call" || action == "fold") -> true
            previousAction == "raise" && (action == "call" || action == "fold") -> true
            else -> false
        }

        // Check all possible escalation scenarios
        val isEscalation = when {
            // From call to higher action
            previousAction == "call" && (action == "3bet" || action == "4bet") -> true
            // From fold to any action
            previousAction == "fold" && (action == "raise" || action == "call" || action == "3bet" || action == "4bet") -> true
            // From lower bet to higher bet
            previousAction == "raise" && (action == "3bet" || action == "4bet") -> true
            previousAction == "3bet" && action == "4bet" -> true
            else -> false
        }

        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        // Check if this is the user's selected position
        val isUserPosition = position == positionDropdown.text.toString()

        if ((isUserPosition && (action == "raise" || action == "3bet" || action == "4bet")) ||
            (position == firstRaisePosition && action == "4bet")) {
            // Handle user's aggressive action
            when (action) {
                "raise" -> {
                    firstRaisePosition = position
                    updateTableAfterUserAction(position, "raise")
                    updateSubsequentActions(position, "raise")
                }
                "3bet" -> {
                    first3BetPosition = position
                    updateTableAfterUserAction(position, "3bet")
                    updateSubsequentActions(position, "3bet")
                }
                "4bet" -> {
                    first4BetPosition = position
                    updateTableAfterUserAction(position, "4bet")
                    //updateSubsequentActions(position, "4bet")
                }
            }
        } else if (isDeEscalation) {
            when {
                position == first3BetPosition -> {
                    first3BetPosition = null
                    first4BetPosition = null
                    resetPositionsAfter(position, action)
                    if (firstRaisePosition != null) {
                        updateSubsequentActions(firstRaisePosition!!, "raise")
                    }
                }
                position == first4BetPosition -> {
                    first4BetPosition = null
                    resetPositionsAfter(position, action)
                    if (first3BetPosition != null) {
                        updateSubsequentActions(first3BetPosition!!, "3bet")
                    }
                }
                position == firstRaisePosition -> {
                    firstRaisePosition = null
                    first3BetPosition = null
                    first4BetPosition = null
                    resetPositionsAfter(position, action)
                }
            }
        } else if (isEscalation) {
            when (action) {
                "raise" -> {
                    // If escalating to a raise, reset any higher actions
                    if (first3BetPosition != null &&
                        allPositions.indexOf(first3BetPosition!!) > allPositions.indexOf(position)) {
                        first3BetPosition = null
                        first4BetPosition = null
                    }
                    firstRaisePosition = position
                    resetPositionsAfter(position, action)
                    updateSubsequentActions(position, "raise")
                }
                "3bet" -> {
                    // If escalating to a 3bet, reset any existing 3bets and 4bets that come after
                    if (first3BetPosition != null &&
                        allPositions.indexOf(first3BetPosition!!) > allPositions.indexOf(position)) {
                        first3BetPosition = position
                        first4BetPosition = null
                        resetPositionsAfter(position, action)
                    } else {
                        first3BetPosition = position
                    }
                    updateSubsequentActions(position, "3bet")
                }
                "4bet" -> {
                    // If escalating to a 4bet, reset any existing 4bets that come after
                    if (first4BetPosition != null &&
                        allPositions.indexOf(first4BetPosition!!) > allPositions.indexOf(position)) {
                        first4BetPosition = position
                        resetPositionsAfter(position, action)
                    } else {
                        first4BetPosition = position
                    }
                    updateSubsequentActions(position, "4bet")
                }
            }
        } else {
            // Handle new actions (when there was no previous action)
            when (action) {
                "raise" -> {
                    firstRaisePosition = position
                    updateSubsequentActions(position, "raise")
                }
                "3bet" -> {
                    first3BetPosition = position
                    updateSubsequentActions(position, "3bet")
                }
                "4bet" -> {
                    first4BetPosition = position
                    updateSubsequentActions(position, "4bet")
                }
                "call" -> {
                    // No need to update subsequent actions for calls
                }
                "fold" -> {
                    // No need to update subsequent actions for folds
                }
            }
        }

        // Make sure all buttons reflect their correct state
        for (i in 0 until actionTable.childCount) {
            val row = actionTable.getChildAt(i) as? TableRow ?: continue
            val posText = (row.getChildAt(0) as? TextView)?.text?.toString() ?: continue
            val buttonLayout = row.getChildAt(1) as? LinearLayout ?: continue

            for (j in 0 until buttonLayout.childCount) {
                val button = buttonLayout.getChildAt(j) as? MaterialButton ?: continue
                val buttonAction = button.text.toString().lowercase()
                updateButtonAppearance(button, positionActions[posText] == buttonAction)
            }
        }

        updateActionSummary()
    }

    private fun resetPositionsAfter(position: String, newAction: String) {  // Added newAction parameter
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val posIndex = allPositions.indexOf(position)
        if (posIndex == -1) return

        // Remove actions only for positions after the current position
        for (i in (posIndex + 1)..allPositions.lastIndex) {
            positionActions.remove(allPositions[i])
        }

        // Update UI for positions after the reset point
        for (i in 0 until actionTable.childCount) {
            val row = actionTable.getChildAt(i) as? TableRow ?: continue
            val posText = (row.getChildAt(0) as? TextView)?.text?.toString() ?: continue

            if (allPositions.indexOf(posText) > posIndex) {
                // Remove existing buttons
                val buttonsLayout = row.getChildAt(1) as LinearLayout
                buttonsLayout.removeAllViews()

                // Add appropriate buttons based on the last valid action
                val buttonActions = when {
                    first3BetPosition != null -> listOf("fold", "call", "4bet")
                    firstRaisePosition != null -> listOf("fold", "call", "3bet")
                    else -> listOf("fold", "raise")
                }

                // Create and add buttons
                val buttons = buttonActions.map { buttonAction ->
                    createActionButton(buttonAction, posText, false).also {
                        buttonsLayout.addView(it)
                    }
                }

                // If the position that triggered the reset called, highlight call for this position
                // Otherwise highlight fold
                val defaultAction = if (position == posText && newAction == "call") "call" else "fold"

                buttons.find { it.text.toString().lowercase() == defaultAction }?.let { buttonToHighlight ->
                    updateButtonAppearance(buttonToHighlight, true)
                    // Update the position actions map
                    positionActions[posText] = defaultAction
                }
            }
        }
    }

    private fun updateButtonAppearance(button: MaterialButton, shouldBeSelected: Boolean) {  // renamed parameter
        button.apply {
            if (shouldBeSelected) {
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.primary_purple))
                setTextColor(getColor(R.color.white))
                isSelected = true
            } else {
                backgroundTintList = ColorStateList.valueOf(getColor(R.color.white))
                setTextColor(getColor(R.color.dark_purple))
                isSelected = false
            }
            invalidate()  // Force redraw
        }
    }

    private fun updateSubsequentActions(position: String, action: String) {
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val posIndex = allPositions.indexOf(position)
        if (posIndex == -1) return

        // Update subsequent rows
        for (i in 0 until actionTable.childCount) {
            val row = actionTable.getChildAt(i) as? TableRow ?: continue
            val posText = (row.getChildAt(0) as? TextView)?.text?.toString() ?: continue

            // Don't modify any rows that have already acted
            if ((firstRaisePosition == posText && positionActions[posText] == "raise") ||
                (first3BetPosition == posText && positionActions[posText] == "3bet")) {
                continue
            }

            val isPositionAfterUser = allPositions.indexOf(posText) > posIndex
            val isPreviousActor = when(action) {
                "3bet" -> posText == firstRaisePosition
                "4bet" -> posText == first3BetPosition
                else -> false
            }

            if (isPositionAfterUser || isPreviousActor) {
                val buttonsLayout = row.getChildAt(1) as LinearLayout
                buttonsLayout.removeAllViews()

                val newButtons = when {
                    action == "raise" -> listOf("fold", "call", "3bet")
                    action == "3bet" && isPreviousActor -> listOf("fold", "call", "4bet")
                    action == "3bet" -> listOf("fold", "call", "4bet")
                    action == "4bet" -> listOf("fold", "all in")  // Changed to just fold/all-in
                    else -> listOf("fold", "raise")
                }

                newButtons.forEach { buttonAction ->
                    buttonsLayout.addView(createActionButton(buttonAction, posText, false))
                }
            }
        }
    }












    private fun resetBettingStates() {
        firstRaisePosition = null
        first3BetPosition = null
        first4BetPosition = null
    }

    private fun updateActionSummary() {
        val actions = positionActions.entries
            .filter { it.value != "fold" }
            .joinToString(", ") { "${it.key} ${it.value}s" }

        actionSummaryText.text = actions
        actionSummaryText.visibility = if (actions.isNotEmpty()) View.VISIBLE else View.GONE
    }
















    private fun setupPreviousActionInfo() {
        val infoIcon = findViewById<ImageView>(R.id.previousActionInfoIcon)
        infoIcon.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_previous_action_info)

            // Round the corners of the dialog window
            dialog.window?.apply {
                setBackgroundDrawableResource(R.drawable.card_background)
                // Set the dialog width to 90% of screen width
                val displayMetrics = resources.displayMetrics
                val width = (displayMetrics.widthPixels * 0.9).toInt()
                setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
            }

            // Add a button at the bottom
            val button = dialog.findViewById<MaterialButton>(R.id.gotItButton)
            button?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun setupGetAdviceButton() {
        getAdviceButton = findViewById(R.id.getAdviceButton)

        getAdviceButton.setOnClickListener {
            try {
                val card1 = card1TextView.text.toString()
                val card2 = card2TextView.text.toString()
                val position = positionDropdown.text.toString()

                // Convert actions to previous action string
                val previousAction = positionActions.entries
                    .filter { it.value != "fold" }
                    .joinToString(", ") { "${it.key} ${it.value}s" }

                // Convert table size from currentTableSize property
                val tableSize = when(currentTableSize) {
                    "6 Players" -> TableSize.SIX_MAX
                    "9 Players" -> TableSize.NINE_MAX
                    else -> TableSize.SIX_MAX // Default to 6 max if something goes wrong
                }

                if (position == "BB" && previousAction.isEmpty()) {
                    Toast.makeText(this, "BB can't be RFI, please input previous action", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (card1.isNotEmpty() && card2.isNotEmpty() && position != "Select Position" && position != "") {
                    try {
                        val (advice, explanation, handStrength) = getAdviceAndStrength(card1, card2, position, previousAction, tableSize)

                        // Create and save the hand record
                        val handRecord = HandRecord(
                            card1 = card1,
                            card2 = card2,
                            tableSize = currentTableSize,  // Use currentTableSize here
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

                    } catch (e: IllegalStateException) {
                        // Handle 4-bet situations
                        Toast.makeText(this, "Sorry! PreFlop Pal does not handle actions beyond 3-bets yet.", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                } else {
                    val missingItems = mutableListOf<String>()
                    if (card1.isEmpty() || card2.isEmpty()) missingItems.add("cards")
                    if (position == "Select Position" || position == "") missingItems.add("position")

                    val missingItemsText = missingItems.joinToString(", ")
                    Toast.makeText(this, "Please select $missingItemsText before getting advice", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                // Catch any other unexpected errors
                Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getAdviceAndStrength(card1: String, card2: String, position: String, previousAction: String, tableSize: TableSize): Triple<String, String, Double> {
        val hand = Hand(Card(card1[0], card1[1]), Card(card2[0], card2[1]))
        return PokerLogic.getAdvice(hand, position, previousAction, tableSize)
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
                    startActivity(Intent(this, LearnActivity::class.java))
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

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}