package com.example.worldcinematest

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.worldcinematest.databinding.ActivitySignInBinding
import com.google.android.material.snackbar.Snackbar

class SignInActivity : AppCompatActivity() {

    private lateinit var signin: ActivitySignInBinding
    private lateinit var shPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signin = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signin.root)

        shPref = getSharedPreferences("userWorldCinema", MODE_PRIVATE)

        signin.apply {

            if (shPref.getString("email", "").toString().isNotEmpty()) {
                etEmail.setText(shPref.getString("email", "").toString())
                etPassword.setText(shPref.getString("password", "").toString())
            }

            buttonSignIn.setOnClickListener {

                if (etEmail.text.toString().isNotEmpty() && etPassword.text.toString()
                        .isNotEmpty()
                ) {
                    if (etEmail.text.toString() == shPref.getString("email", "")
                            .toString() && etPassword.text.toString() == shPref.getString(
                            "password",
                            ""
                        ).toString()
                    ) {
                        shPref.edit().putBoolean("logout", false).apply()
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            R.string.invalid_input,
                            Toast.LENGTH_SHORT
                        ).show()
                        Click(it)
                    }
                } else {
                    Toast.makeText(this@SignInActivity, R.string.empty_field, Toast.LENGTH_SHORT)
                        .show()
                }
            }

            buttonSignUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                finish()
            }
        }
    }

    fun Click(view: View) {
        var snackbar = Snackbar.make(view, R.string.would_you_like, Snackbar.LENGTH_LONG)
        snackbar.setTextColor(WHITE)
        snackbar.setActionTextColor(BLACK)
        snackbar.setAction(R.string.yes, View.OnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            finish()
        }).show()
    }
}