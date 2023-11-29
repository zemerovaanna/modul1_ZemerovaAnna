package com.example.worldcinematest

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worldcinematest.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), MenuItemAdapter.Listener {

    private lateinit var profile: FragmentProfileBinding
    private var adapter = MenuItemAdapter(this)
    private lateinit var shPref: SharedPreferences
    private lateinit var database: AppDatabase

    private val menuList = listOf(
        MenuItem(0, R.drawable.ic_discussion, "Обсуждения"),
        MenuItem(1, R.drawable.ic_history, "История"),
        MenuItem(2, R.drawable.ic_settings, "Настройки")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profile = FragmentProfileBinding.inflate(inflater, container, false)
        return profile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shPref = requireActivity().getSharedPreferences(
            "userWorldCinema",
            AppCompatActivity.MODE_PRIVATE
        )

        profile.apply {
            ProfileName.text = shPref.getString("name", "").toString()
            ProfileLastname.text = shPref.getString("lastname", "").toString()
            ProfileEmail.text = shPref.getString("email", "").toString()
            Menu.layoutManager = LinearLayoutManager(requireContext())
            Menu.adapter = adapter
            adapter.addDiscussion(menuList)

            buttonLogOut.setOnClickListener {
                database = AppDatabase.getInstance(requireContext())
                database.myCollectionDao().deleteAll()
                shPref.edit().putBoolean("logout", true).apply()
                startActivity(
                    Intent(
                        this@ProfileFragment.requireContext(),
                        SignInActivity::class.java
                    )
                )
                activity?.finish()
            }
        }
    }

    override fun onClick(itemId: Int) {
        when (itemId) {
            0 -> {
                startActivity(
                    Intent(
                        this@ProfileFragment.requireContext(),
                        ChatListActivity::class.java
                    )
                )
            }
        }
    }
}