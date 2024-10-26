package com.ryanluo.prefloppal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)

        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        val signUpButton = findViewById<MaterialButton>(R.id.signUpButton)

        loginButton.setOnClickListener {
            LoginActivity.startActivity(this)
        }

        signUpButton.setOnClickListener {
            // We'll implement this later
            SignUpActivity.startActivity(this)
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, WelcomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}