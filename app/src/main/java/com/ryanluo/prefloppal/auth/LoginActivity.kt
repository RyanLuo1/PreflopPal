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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ryanluo.prefloppal.main.MainActivity
import com.ryanluo.prefloppal.R
import com.ryanluo.prefloppal.utils.FirebaseManager

class LoginActivity : AppCompatActivity() {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var firebaseManager: FirebaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Initialize FirebaseManager
        firebaseManager = FirebaseManager.getInstance()

        // Back button logic
        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }

        // Initialize input layouts and fields
        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val loginButton = findViewById<MaterialButton>(R.id.loginSubmitButton)

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
                // Disable the login button to prevent multiple clicks during the login process
                loginButton.isEnabled = false

                // Call FirebaseManager to sign in the user
                firebaseManager.signIn(email, password) { success, error ->
                    runOnUiThread {
                        // Re-enable the login button after the sign-in process is complete
                        loginButton.isEnabled = true
                        if (success) {
                            // Navigate to MainActivity on successful login
                            MainActivity.startActivity(this)
                            finish()
                        } else {
                            // Show error message if login fails
                            Toast.makeText(this, error ?: "Login failed", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
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

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}
