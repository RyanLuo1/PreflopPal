package com.ryanluo.prefloppal.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.adapters.FaqAdapter
import com.ryanluo.prefloppal.adapters.TermAdapter
import com.ryanluo.prefloppal.data.getFaqItems
import com.ryanluo.prefloppal.data.getTermItems

class LearnActivity : AppCompatActivity() {

    private val faqItems = getFaqItems()
    private val termItems = getTermItems()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_activity)

        setupWelcomeText()
        setupPracticeButton()

        setupMenuIcon()
        setupBottomNavigation()
        setupBackButton()

        // Show practice content by default
        findViewById<View>(R.id.practiceContent).visibility = View.VISIBLE
    }

    private fun setupWelcomeText() {
        val welcomeText = findViewById<TextView>(R.id.welcomeText)
        val text = SpannableStringBuilder(
            "Welcome to the Preflop Practice Mode" +
                    "\n\nThis comprehensive tool will help you master poker strategy from the very first action. This interactive section is designed to help simulate real-world preflop scenarios and sharpen your decision-making skills. Whether you're a beginner looking to learn the fundamentals or an experienced player refining your edge, this tool offers a dynamic way to practice and improve." +
                    "\n\nIn addition to the practice mode, we've included an FAQ page to answer common questions and Terminology page to familiarize you with essential poker terms. These resources ensure you have the support and knowledge needed to confidently navigate and enhance your poker game." +
                    "\n\nDive in, explore, and start your journey to becoming a more skilled and strategic poker player!" +
                    "\n\nNote: Practice Mode is currently under development and will be available soon. Stay tuned!"
        )

        // Make "Preflop Practice Mode" bold
        text.setSpan(
            StyleSpan(Typeface.BOLD),
            "Welcome to the ".length,
            "Welcome to the Preflop Practice Mode".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make "FAQ page" bold
        text.setSpan(
            StyleSpan(Typeface.BOLD),
            text.indexOf("FAQ page"),
            text.indexOf("FAQ page") + "FAQ page".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make "Terminology page" bold
        text.setSpan(
            StyleSpan(Typeface.BOLD),
            text.indexOf("Terminology page"),
            text.indexOf("Terminology page") + "Terminology page".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Make the note bold and styled
        val noteStart = text.indexOf("Note:")
        text.setSpan(
            StyleSpan(Typeface.BOLD_ITALIC),
            noteStart,
            text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        welcomeText.text = text
    }

    private fun setupPracticeButton() {
        // Make sure the ID matches the one in your XML
        findViewById<MaterialButton>(R.id.enterPracticeButton).setOnClickListener {
            // Start the PracticeModeActivity
            val intent = Intent(this, PracticeModeActivity::class.java)
            startActivity(intent)
        }
    }






    private fun setupMenuIcon() {
        findViewById<ImageView>(R.id.menuIcon).setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun setupBackButton() {
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            showContent(ContentType.PRACTICE)
        }
    }

    private fun showPopupMenu(view: View) {
        PopupMenu(this, view).apply {
            menuInflater.inflate(R.menu.learn_menu, menu)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_faqs -> {
                        showContent(ContentType.FAQ)
                        true
                    }
                    R.id.menu_terminology -> {
                        showContent(ContentType.TERMINOLOGY)
                        true
                    }
                    else -> false
                }
            }
            show()
        }
    }

    private enum class ContentType {
        FAQ, TERMINOLOGY, PRACTICE
    }

    private fun showContent(contentType: ContentType) {
        // Hide all content first
        findViewById<View>(R.id.faqContent).visibility = View.GONE
        findViewById<View>(R.id.terminologyContent).visibility = View.GONE
        findViewById<View>(R.id.practiceContent).visibility = View.GONE

        // Get toolbar elements
        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val backButton = findViewById<ImageView>(R.id.backButton)

        // Show selected content
        when (contentType) {
            ContentType.FAQ -> {
                toolbarTitle.text = "Frequently Asked Questions"
                backButton.visibility = View.VISIBLE
                val faqContent = findViewById<View>(R.id.faqContent)
                faqContent.visibility = View.VISIBLE
                setupFaqRecyclerView()
            }
            ContentType.TERMINOLOGY -> {
                toolbarTitle.text = "Common Poker Terms"
                backButton.visibility = View.VISIBLE
                val termContent = findViewById<View>(R.id.terminologyContent)
                termContent.visibility = View.VISIBLE
                setupTermRecyclerView()
            }
            ContentType.PRACTICE -> {
                toolbarTitle.text = "Learn Poker Basics!"
                backButton.visibility = View.GONE
                findViewById<View>(R.id.practiceContent).visibility = View.VISIBLE
            }
        }
    }

    private fun setupFaqRecyclerView() {
        findViewById<RecyclerView>(R.id.faqRecyclerView)?.let { recyclerView ->
            if (recyclerView.adapter == null) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = FaqAdapter(faqItems)
                }
            }
        }
    }

    private fun setupTermRecyclerView() {
        findViewById<RecyclerView>(R.id.terminologyRecyclerView)?.let { recyclerView ->
            if (recyclerView.adapter == null) {
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = TermAdapter(termItems)
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        findViewById<BottomNavigationView>(R.id.bottomNavigation).apply {
            selectedItemId = R.id.navigation_learn

            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_history -> {
                        startActivity(Intent(this@LearnActivity, HistoryActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                        true
                    }
                    R.id.navigation_home -> {
                        startActivity(Intent(this@LearnActivity, MainActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                        true
                    }
                    R.id.navigation_learn -> {
                        true // Already here
                    }
                    else -> false
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}