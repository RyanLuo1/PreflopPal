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
import androidx.core.content.res.ResourcesCompat
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
        setupResetButton()
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
        positionDropdown.dropDownHeight = 850

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
        // Clear ALL state
        actionTable.removeAllViews()
        positionActions.clear()
        resetBettingStates()

        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val relevantPositions = allPositions.takeWhile { it != selectedPosition } + selectedPosition

        // Add rows for each relevant position with fresh state
        relevantPositions.forEach { position ->
            addPositionRow(position, position == selectedPosition)
        }

        actionTable.visibility = View.VISIBLE
    }

    private fun updateTableAfterUserAction(userPosition: String, userAction: String) {
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        // If this is a 4bet, update appropriate rows to fold/all-in
        if (userAction == "4bet") {
            if (userPosition == firstRaisePosition) {
                // If original raiser is 4betting, only update 3bettor's response row
                first3BetPosition?.let { threeBetPos ->
                    for (i in 0 until actionTable.childCount) {
                        val row = actionTable.getChildAt(i) as? TableRow ?: continue
                        val posText = row.getChildAt(0)?.let { it as? TextView }?.text?.toString() ?: continue

                        if (posText == threeBetPos && i > allPositions.indexOf(threeBetPos)) {
                            val buttonsLayout = row.getChildAt(1) as? LinearLayout ?: continue
                            buttonsLayout.removeAllViews()
                            listOf("fold", "all in").forEach { buttonAction ->
                                buttonsLayout.addView(createActionButton(buttonAction, posText, false))
                            }
                        }
                    }
                }
            } else {
                // If someone else is 4betting, update all appropriate rows
                for (i in 0 until actionTable.childCount) {
                    val row = actionTable.getChildAt(i) as? TableRow ?: continue
                    val posText = row.getChildAt(0)?.let { it as? TextView }?.text?.toString() ?: continue

                    if ((posText == firstRaisePosition || posText == first3BetPosition) &&
                        i > allPositions.indexOf(posText!!) ||
                        allPositions.indexOf(posText) > allPositions.indexOf(userPosition)) {

                        val buttonsLayout = row.getChildAt(1) as? LinearLayout ?: continue
                        buttonsLayout.removeAllViews()
                        listOf("fold", "all in").forEach { buttonAction ->
                            buttonsLayout.addView(createActionButton(buttonAction, posText, false))
                        }
                    }
                }
            }
        }

        // Determine which positions need to show up
        val positionsToAdd = mutableListOf<String>()

        when (userAction) {
            "raise" -> {
                val userIndex = allPositions.indexOf(userPosition)
                if (userIndex != -1) {
                    // Clear any existing rows first
                    var i = actionTable.childCount - 1
                    while (i >= 0) {
                        val row = actionTable.getChildAt(i) as? TableRow
                        val posText = row?.getChildAt(0)?.let { it as? TextView }?.text?.toString()
                        if (posText != null && allPositions.indexOf(posText) > userIndex) {
                            actionTable.removeViewAt(i)
                        }
                        i--
                    }
                    // Then add new rows
                    positionsToAdd.addAll(allPositions.subList(userIndex + 1, allPositions.size))
                }
            }
            "3bet" -> {
                // First add all positions through BB
                val userIndex = allPositions.indexOf(userPosition)
                if (userIndex != -1 && userPosition == positionDropdown.text.toString()) {
                    val positionsToShow = allPositions.subList(userIndex + 1, allPositions.size)
                    positionsToShow.forEach { position ->
                        var exists = false
                        for (i in 0 until actionTable.childCount) {
                            val row = actionTable.getChildAt(i) as? TableRow
                            val posText = row?.getChildAt(0)?.let { it as? TextView }?.text?.toString()
                            if (posText == position) {
                                exists = true
                                break
                            }
                        }
                        if (!exists) {
                            positionsToAdd.add(position)
                        }
                    }
                }

                // Then add the original raiser's response row last
                firstRaisePosition?.let { raisePos ->
                    positionsToAdd.add(raisePos)
                }
            }
            "4bet" -> {
                // Only add the 3bettor's response if they don't already have a response row
                first3BetPosition?.let { threeBetPos ->
                    if (!hasResponseRow(threeBetPos)) {
                        positionsToAdd.add(threeBetPos)
                    }
                }
            }
        }

        // Add the new positions to the table
        positionsToAdd.forEach { position ->
            when (userAction) {
                "raise" -> {
                    addPositionRow(position, false)
                }
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
            }
        }
    }

    private fun setupResetButton() {
        findViewById<ImageView>(R.id.resetButton).setOnClickListener {
            // Clear action states
            resetBettingStates()
            positionActions.clear()

            // Get the current position
            val selectedPosition = positionDropdown.text.toString()

            // Reset the table to show only up to the user's position
            updateActionTableForPosition(selectedPosition)

            // Reset the action summary
            updateActionSummary()
        }
    }

    private fun hasResponseRow(position: String): Boolean {
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

        val positionIndex = allPositions.indexOf(position)
        for (i in 0 until actionTable.childCount) {
            val row = actionTable.getChildAt(i) as? TableRow ?: continue
            val posText = row.getChildAt(0)?.let { it as? TextView }?.text?.toString()
            if (posText == position && i > positionIndex) {
                return true
            }
        }
        return false
    }

    private fun addPositionRow(position: String, isSelectedPosition: Boolean) {
        val customFont = ResourcesCompat.getFont(this, R.font.nunito_bold)

        val row = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            setPadding(8, 8, 8, 8)

            // Add background color for user's position
            if (isSelectedPosition) {
                setBackgroundColor(getColor(R.color.light_purple))  // Use your light purple color
            }

        }

        // Position name
        TextView(this).apply {
            text = position
            textSize = 18f
            setPadding(16, 8, 16, 8)
            setTextColor(getColor(R.color.dark_purple))
            typeface = customFont
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
        val customFont = ResourcesCompat.getFont(this, R.font.nunito_bold)

        val row = TableRow(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            setPadding(8, 8, 8, 8)

            // Add background color for user's position
            if (position == positionDropdown.text.toString()) {
                setBackgroundColor(getColor(R.color.light_purple))
            }
        }

        // Position name
        TextView(this).apply {
            text = position
            textSize = 18f
            setPadding(16, 8, 16, 8)
            setTextColor(getColor(R.color.dark_purple))
            typeface = customFont
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
            setTypeface(typeface, Typeface.BOLD)
            textSize = 16f
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

            // Center the text within the button
            gravity = Gravity.CENTER

            setOnClickListener {
                handleActionClick(this, position, action)
            }
        }
    }

    private fun handleActionClick(clickedButton: MaterialButton, position: String, action: String) {

        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }

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
            previousAction == "call" && (action == "3bet" || action == "4bet") -> true
            previousAction == "fold" && (action == "raise" || action == "call" || action == "3bet" || action == "4bet") -> true
            previousAction == "raise" && (action == "3bet" || action == "4bet") -> true
            previousAction == "3bet" && action == "4bet" -> true
            else -> false
        }

        // Check if this is the user's selected position
        val isUserPosition = position == positionDropdown.text.toString()

        if (isUserPosition && (action == "raise" || action == "3bet" || action == "4bet")) {
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
                }
            }
        } else if (isDeEscalation) {
            when {
                position == firstRaisePosition -> {

                    // When original raiser de-escalates, remove ALL response rows and reset everything
                    var i = 0
                    while (i < actionTable.childCount) {
                        val row = actionTable.getChildAt(i) as? TableRow ?: continue
                        val posText =
                            row.getChildAt(0)?.let { it as? TextView }?.text?.toString() ?: continue

                        // Remove any response rows (both 3bet and 4bet responses)
                        if ((posText == firstRaisePosition || posText == first3BetPosition) &&
                            i > allPositions.indexOf(posText!!)
                        ) {
                            actionTable.removeViewAt(i)
                        } else {
                            i++
                        }
                    }

                    // Clear all betting states
                    firstRaisePosition = null
                    first3BetPosition = null
                    first4BetPosition = null

                    // Reset remaining positions
                    resetPositionsAfter(position, action)
                }

                position == first3BetPosition -> {
                    // Remove any response rows that appeared after this 3bet
                    var i = 0
                    while (i < actionTable.childCount) {
                        val row = actionTable.getChildAt(i) as? TableRow ?: continue
                        val posText =
                            row.getChildAt(0)?.let { it as? TextView }?.text?.toString() ?: continue

                        // Remove original raiser's response row and any 4bet related rows
                        if ((posText == firstRaisePosition || posText == first3BetPosition) &&
                            i > allPositions.indexOf(posText!!)
                        ) {
                            actionTable.removeViewAt(i)
                        } else {
                            i++
                        }
                    }

                    first3BetPosition = null
                    first4BetPosition = null
                    resetPositionsAfter(position, action)
                    if (firstRaisePosition != null) {
                        updateSubsequentActions(firstRaisePosition!!, "raise")
                    }
                }

                position == first4BetPosition -> {
                    println("DEBUG: 4bettor (${position}) de-escalating from 4bet to ${action}")

                    // Store who was the 4bettor before we null it
                    val was4Bettor = first4BetPosition

                    // Clear 4bet state first
                    first4BetPosition = null

                    // If original raiser was the 4bettor
                    if (was4Bettor == firstRaisePosition) {
                        // Remove only the 3bettor's fold/all-in row
                        var i = 0
                        while (i < actionTable.childCount) {
                            val row = actionTable.getChildAt(i) as? TableRow ?: continue
                            val posText =
                                row.getChildAt(0)?.let { it as? TextView }?.text?.toString()

                            if (posText == first3BetPosition && i > allPositions.indexOf(
                                    first3BetPosition!!
                                )
                            ) {
                                actionTable.removeViewAt(i)
                            } else {
                                i++
                            }
                        }
                    }
                    // If someone else was the 4bettor
                    else {
                        // First update all positions after the 4bettor back to fold/call/4bet
                        for (i in 0 until actionTable.childCount) {
                            val row = actionTable.getChildAt(i) as? TableRow ?: continue
                            val posText =
                                row.getChildAt(0)?.let { it as? TextView }?.text?.toString()
                                    ?: continue

                            if (allPositions.indexOf(posText) > allPositions.indexOf(position)) {
                                val buttonsLayout = row.getChildAt(1) as LinearLayout
                                buttonsLayout.removeAllViews()
                                listOf("fold", "call", "4bet").forEach { buttonAction ->
                                    buttonsLayout.addView(
                                        createActionButton(
                                            buttonAction,
                                            posText,
                                            false
                                        )
                                    )
                                }
                            }
                        }

                        // Remove all fold/all-in response rows
                        var i = 0
                        while (i < actionTable.childCount) {
                            val row = actionTable.getChildAt(i) as? TableRow ?: continue
                            val posText =
                                row.getChildAt(0)?.let { it as? TextView }?.text?.toString()

                            // Remove response rows for both original raiser and 3bettor
                            if ((posText == firstRaisePosition || posText == first3BetPosition) &&
                                i > allPositions.indexOf(posText!!)
                            ) {
                                actionTable.removeViewAt(i)
                            } else {
                                i++
                            }
                        }

                        // Add back the appropriate response row for the original raiser if needed
                        if (first3BetPosition != null) {
                            firstRaisePosition?.let { raisePos ->
                                addPositionRowWithOptions(raisePos, listOf("fold", "call", "4bet"))
                            }
                        }
                    }
                }
            }
        } else if (isEscalation) {
            when (action) {
                "raise" -> {
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
                    first3BetPosition = position
                    updateTableAfterUserAction(position, "3bet")
                    updateSubsequentActions(position, "3bet")
                }
                "4bet" -> {
                    if (first4BetPosition != null &&
                        allPositions.indexOf(first4BetPosition!!) > allPositions.indexOf(position)) {
                        first4BetPosition = position
                        resetPositionsAfter(position, action)
                    } else {
                        first4BetPosition = position
                    }
                    updateTableAfterUserAction(position, "4bet")
                }
            }
        } else {
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
                    updateTableAfterUserAction(position, "4bet")
                }
            }
        }

        // Make sure all buttons reflect their correct state
        // Make sure all buttons reflect their correct state
        for (i in 0 until actionTable.childCount) {
            val row = actionTable.getChildAt(i) as? TableRow ?: continue
            val posText = (row.getChildAt(0) as? TextView)?.text?.toString() ?: continue
            val buttonLayout = row.getChildAt(1) as? LinearLayout ?: continue

            // Flag to indicate whether this position has been processed in a special case
            var specialCaseHandled = false

            // Special case: if this is the original raiser's first row and they later go all in,
            if (posText == firstRaisePosition && i == allPositions.indexOf(firstRaisePosition!!) &&
                (positionActions[posText] == "4bet" || positionActions[posText] == "all in")
            ) {
                for (j in 0 until buttonLayout.childCount) {
                    val button = buttonLayout.getChildAt(j) as? MaterialButton ?: continue
                    val buttonAction = button.text.toString().lowercase()
                    updateButtonAppearance(button, buttonAction == "raise")
                }
                specialCaseHandled = true // Set the flag
            }

            // Special case: if this is the position that made the first 3bet and they later go all in,
            // keep the 3bet button highlighted
            if (posText == first3BetPosition && i == allPositions.indexOf(first3BetPosition!!) &&
                positionActions[posText] == "all in"
            ) {
                for (j in 0 until buttonLayout.childCount) {
                    val button = buttonLayout.getChildAt(j) as? MaterialButton ?: continue
                    val buttonAction = button.text.toString().lowercase()
                    updateButtonAppearance(button, buttonAction == "3bet")
                }
                specialCaseHandled = true // Set the flag
            }

            // Normal button state update only if no special case was handled
            if (!specialCaseHandled) {
                for (j in 0 until buttonLayout.childCount) {
                    val button = buttonLayout.getChildAt(j) as? MaterialButton ?: continue
                    val buttonAction = button.text.toString().lowercase()
                    updateButtonAppearance(button, positionActions[posText] == buttonAction)
                }
            }
        }

        updateActionSummary()
    }

    private fun resetPositionsAfter(position: String, newAction: String) {
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

                // Only highlight default action if it's not the user's position
                if (posText != positionDropdown.text.toString()) {
                    val defaultAction = if (position == posText && newAction == "call") "call" else "fold"
                    buttons.find { it.text.toString().lowercase() == defaultAction }?.let { buttonToHighlight ->
                        updateButtonAppearance(buttonToHighlight, true)
                        positionActions[posText] = defaultAction
                    }
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
        val actionSequence = mutableListOf<String>()

        // Add the initial raise if it exists
        if (firstRaisePosition != null) {
            actionSequence.add("${firstRaisePosition} raises")

            // Add any calls or folds between raise and 3bet
            positionActions.entries.forEach { (position, action) ->
                val posIndex = getPositionIndex(position)
                val raiseIndex = getPositionIndex(firstRaisePosition!!)
                val threeBetIndex = if (first3BetPosition != null) getPositionIndex(first3BetPosition!!) else -1

                // Only add actions that occurred after raise but before 3bet
                if (posIndex > raiseIndex && (threeBetIndex == -1 || posIndex < threeBetIndex)) {
                    if (action == "call") actionSequence.add("$position calls")
                }
            }
        }

        // Add the 3bet if it exists
        if (first3BetPosition != null) {
            actionSequence.add("${first3BetPosition} 3-bets")

            // Add any calls or folds between 3bet and 4bet
            positionActions.entries.forEach { (position, action) ->
                val posIndex = getPositionIndex(position)
                val threeBetIndex = getPositionIndex(first3BetPosition!!)
                val fourBetIndex = if (first4BetPosition != null) getPositionIndex(first4BetPosition!!) else -1

                if (posIndex > threeBetIndex && (fourBetIndex == -1 || posIndex < fourBetIndex)) {
                    if (action == "call") actionSequence.add("$position calls")
                }
            }
        }

        // Add the 4bet if it exists
        if (first4BetPosition != null) {
            actionSequence.add("${first4BetPosition} 4-bets")

            // Add any all-ins after 4bet (from any position)
            positionActions.entries.forEach { (position, action) ->
                if (action == "all in") {
                    actionSequence.add("$position All-in")
                }
            }
        }

        actionSummaryText.text = actionSequence.joinToString(", ")
        actionSummaryText.visibility = if (actionSequence.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun getPositionIndex(position: String): Int {
        val allPositions = when(currentTableSize) {
            "9 Players" -> listOf("UTG", "UTG+1", "UTG+2", "LJ", "HJ", "CO", "BTN", "SB", "BB")
            else -> listOf("UTG", "MP", "CO", "BTN", "SB", "BB")
        }
        return allPositions.indexOf(position)
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
                val currentAction = actionSummaryText.text.toString()
                val tableSize = when(currentTableSize) {
                    "6 Players" -> TableSize.SIX_MAX
                    "9 Players" -> TableSize.NINE_MAX
                    else -> TableSize.NINE_MAX
                }

                if (currentAction.isNotEmpty()) {
                    val lastAction = currentAction.split(", ").last()
                    val lastPosition = lastAction.split(" ")[0]  // Get first word which is the position
                    if (lastPosition == position) {
                        Toast.makeText(this, "There is no advice if you were the last position to act.", Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }
                }

                if (position == "BB" && currentAction.isEmpty() || currentAction == "BB raises") {
                    Toast.makeText(this, "BB can't be RFI, please input previous action", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                if (card1.isNotEmpty() && card2.isNotEmpty() && position != "Select Position" && position != "") {
                    try {
                        val (advice, explanation, handStrength) = getAdviceAndStrength(card1, card2, position, currentAction, tableSize)

                        // Create and save the hand record
                        val handRecord = HandRecord(
                            card1 = card1,
                            card2 = card2,
                            tableSize = currentTableSize,
                            position = position,
                            previousAction = currentAction,
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
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.navigation_home

            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_history -> {
                        startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                        true
                    }
                    R.id.navigation_home -> {
                        true // Already here
                    }
                    R.id.navigation_learn -> {
                        startActivity(Intent(this@MainActivity, LearnActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        finish()
                        true
                    }
                    else -> false
                }
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