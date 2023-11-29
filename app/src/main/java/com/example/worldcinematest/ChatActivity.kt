package com.example.worldcinematest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worldcinematest.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    lateinit var binding: ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent.extras
        if (intent != null) {
            binding.ChatTitle.text = intent.getString("chatname", "")
        }

        binding.Back.setOnClickListener {
            finish()
        }
    }
}