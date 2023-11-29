package com.example.worldcinematest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcinematest.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {

    lateinit var binding: ActivityLaunchBinding
    private lateinit var shPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shPref = getSharedPreferences("userWorldCinema", MODE_PRIVATE)
        if (shPref.getBoolean("logout", true) == true) {
            binding.linearLayout.alpha = 0f
            binding.linearLayout.animate().setDuration(1000).alpha(1f).withEndAction {
                startActivity(Intent(this@LaunchActivity, SignInActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        } else {
            binding.linearLayout.alpha = 0f
            binding.linearLayout.animate().setDuration(1000).alpha(1f).withEndAction {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }

    }
}