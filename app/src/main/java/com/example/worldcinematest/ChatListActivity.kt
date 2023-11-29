package com.example.worldcinematest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldcinematest.databinding.ActivityChatListBinding

class ChatListActivity : AppCompatActivity(), DiscussionAdapter.Listener {

    private lateinit var binding: ActivityChatListBinding
    private var adapter = DiscussionAdapter(this)
    private val dList = listOf(
        Discussion(
            "Игра Престолов",
            R.drawable.game_of_thrones,
            "Иван:",
            "Смотрели уже последнюю серию? Я просто поверить не..."
        ),
        Discussion(
            "Стрела",
            R.drawable.the_arrow,
            "Иван:",
            "Смотрели уже последнюю серию? Я просто поверить не..."
        ),
        Discussion(
            "Джессика Джонс",
            R.drawable.jessica_jones,
            "Иван:",
            "Смотрели уже последнюю серию? Я просто поверить не..."
        ),
        Discussion(
            "Леденящие душу приключения С...",
            R.drawable.chilling_adventures,
            "Иван:",
            "Смотрели уже последнюю серию? Я просто поверить не..."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvDiscussions.layoutManager = LinearLayoutManager(this@ChatListActivity)
            rvDiscussions.adapter = adapter
            adapter.addDiscussion(dList)

            buttonBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun onClick(chatName: String) {
        var intent = Intent(this@ChatListActivity, ChatActivity::class.java)
        intent.putExtra("chatname", chatName)
        startActivity(intent)
    }
}