package com.ryanluo.prefloppal

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

class SignUpActivity : AppCompatActivity() {
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var confirmPasswordInputLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)

        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            finish()
        }

        emailInputLayout = findViewById(R.id.emailInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        confirmPasswordInputLayout = findViewById(R.id.confirmPasswordInputLayout)

        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val passwordInput = findViewById<TextInputEditText>(R.id.passwordInput)
        val confirmPasswordInput = findViewById<TextInputEditText>(R.id.confirmPasswordInput)
        val signUpButton = findViewById<MaterialButton>(R.id.signUpSubmitButton)

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
                // Clear confirm password error if it was a mismatch
                if (confirmPasswordInput.text?.toString() == s.toString()) {
                    confirmPasswordInputLayout.error = null
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        confirmPasswordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                confirmPasswordInputLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        signUpButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (validateInput(email, password, confirmPassword)) {
                // TODO: Implement actual signup logic
                Toast.makeText(this, "Sign up functionality coming soon!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(email: String, password: String, confirmPassword: String): Boolean {
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

        if (confirmPassword.isEmpty()) {
            confirmPasswordInputLayout.error = "Please confirm your password"
            isValid = false
        } else if (password != confirmPassword) {
            confirmPasswordInputLayout.error = "Passwords do not match"
            isValid = false
        }

        return isValid
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }
}