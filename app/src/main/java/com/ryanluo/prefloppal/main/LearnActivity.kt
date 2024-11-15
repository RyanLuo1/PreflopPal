package com.ryanluo.prefloppal.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.adapters.FaqAdapter
import com.ryanluo.prefloppal.adapters.TermAdapter
import com.ryanluo.prefloppal.data.getFaqItems
import com.ryanluo.prefloppal.data.getTermItems

class LearnActivity : AppCompatActivity() {

    private val faqItems = getFaqItems();
    private val termItems = getTermItems();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_activity)

        setupMenuIcon()
        setupBottomNavigation()
        // Welcome content is visible by default in the layout
    }

    private fun setupMenuIcon() {
        findViewById<ImageView>(R.id.menuIcon).setOnClickListener { view ->
            showPopupMenu(view)
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
                    R.id.menu_practice -> {
                        showContent(ContentType.PRACTICE)
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

        // Show selected content
        when (contentType) {

            ContentType.FAQ -> {
                findViewById<TextView>(R.id.toolbarTitle).text = "Frequently Asked Questions"
                val faqContent = findViewById<View>(R.id.faqContent)
                faqContent.visibility = View.VISIBLE
                setupFaqRecyclerView()
            }
            ContentType.TERMINOLOGY -> {
                findViewById<TextView>(R.id.toolbarTitle).text = "Common Poker Terms"
                val termContent = findViewById<View>(R.id.terminologyContent)
                termContent.visibility = View.VISIBLE
                setupTermRecyclerView()
            }
            ContentType.PRACTICE -> {
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


    // LearnActivity (Right Screen)
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