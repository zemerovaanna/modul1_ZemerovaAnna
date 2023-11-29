package com.example.worldcinematest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.worldcinematest.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signup: ActivitySignUpBinding
    private lateinit var shPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signup = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signup.root)

        shPref = getSharedPreferences("userWorldCinema", MODE_PRIVATE)

        signup.apply {

            buttonHaveAcc.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            }

            buttonSignUp.setOnClickListener {

                val name: String = etName.text.toString()
                val lastname: String = etLastname.text.toString()
                val email: String = etEmail.text.toString()
                val password: String = etPassword.text.toString()
                val confirmpassword: String = etConfirmPassword.text.toString()

                if (name.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()) {
                    if (OnlyLetters(name) && OnlyLetters(lastname)) {
                        if (name.length >= 2) {
                            if (name.length <= 20) {
                                if (lastname.length <= 20) {
                                    if (checkEmail(email) == true) {
                                        if (password.length >= 8 && password.length <= 20) {
                                            if (password == confirmpassword) {
                                                if (email != shPref.getString("email", "")) {
                                                    shPref.edit().putString("name", name).apply()
                                                    shPref.edit().putString("lastname", lastname)
                                                        .apply()
                                                    shPref.edit().putString("email", email).apply()
                                                    shPref.edit().putString("password", password)
                                                        .apply()
                                                    shPref.edit().putBoolean("logout", false)
                                                        .apply()
                                                    startActivity(
                                                        Intent(
                                                            this@SignUpActivity,
                                                            SignInActivity::class.java
                                                        )
                                                    )
                                                    finish()
                                                } else {
                                                    Toast.makeText(
                                                        this@SignUpActivity,
                                                        R.string.such_mail_is_already_registered,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            } else {
                                                Toast.makeText(
                                                    this@SignUpActivity,
                                                    R.string.password_mismatch,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        } else {
                                            Toast.makeText(
                                                this@SignUpActivity,
                                                R.string.check_password,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        Toast.makeText(
                                            this@SignUpActivity,
                                            R.string.incorrect_email,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        this@SignUpActivity,
                                        R.string.lastname_too_long,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    R.string.name_too_long,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@SignUpActivity,
                                R.string.name_too_short,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            R.string.only_contain_letters,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        R.string.empty_field,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun OnlyLetters(text: String): Boolean {
        for (i in text) {
            if (!i.isLetter()) {
                return false
            }
        }
        return true
    }

    fun checkEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}