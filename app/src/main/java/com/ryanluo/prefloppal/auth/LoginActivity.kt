package com.ryanluo.prefloppal.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ryanluo.prefloppal.main.MainActivity
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.utils.FirebaseManager

class LoginActivity : AppCompatActivity() {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var firebaseManager: FirebaseManager
    private lateinit var rememberMeCheckbox: MaterialCheckBox

    companion object {
        private const val PREFS_NAME = "LoginPrefs"
        private const val KEY_REMEMBER_ME = "remember_me"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"

        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Initialize FirebaseManager
        firebaseManager = FirebaseManager.getInstance()

        // Initialize views
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = findViewById<MaterialButton>(R.id.loginSubmitButton)
        rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox)

        // Load saved preferences
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val rememberedEmail = prefs.getString(KEY_EMAIL, "")
        val rememberedPassword = prefs.getString(KEY_PASSWORD, "")
        val rememberMe = prefs.getBoolean(KEY_REMEMBER_ME, false)

        // Set remembered values if they exist
        if (rememberMe) {
            rememberMeCheckbox.isChecked = true
            emailInput.setText(rememberedEmail)
            passwordInput.setText(rememberedPassword)
        }

        // Back button logic
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }

        // Clear errors when user starts typing
        emailInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                emailInputLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                passwordInputLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (validateInput(email, password)) {
                loginButton.isEnabled = false

                firebaseManager.signIn(email, password) { success, error ->
                    runOnUiThread {
                        loginButton.isEnabled = true
                        if (success) {
                            if (firebaseManager.isEmailVerified()) {
                                // Save credentials if remember me is checked
                                saveLoginPreferences(email, password)

                                // Navigate to MainActivity
                                MainActivity.startActivity(this)
                                finish()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Please verify your email before logging in",
                                    Toast.LENGTH_LONG
                                ).show()
                                firebaseManager.signOut()
                            }
                        } else {
                            Toast.makeText(this, error ?: "Login failed", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun saveLoginPreferences(email: String, password: String) {
        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean(KEY_REMEMBER_ME, rememberMeCheckbox.isChecked)
            if (rememberMeCheckbox.isChecked) {
                putString(KEY_EMAIL, email)
                putString(KEY_PASSWORD, password)
            } else {
                remove(KEY_EMAIL)
                remove(KEY_PASSWORD)
            }
            apply()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if (email.isEmpty()) {
            emailInputLayout.error = "Please enter an email"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.error = "Please enter a valid email"
            isValid = false
        }

        if (password.isEmpty()) {
            passwordInputLayout.error = "Please enter a password"
            isValid = false
        } else if (password.length < 6) {
            passwordInputLayout.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }
}