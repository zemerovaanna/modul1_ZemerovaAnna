package com.example.worldcinematest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinematest.databinding.DiscussionBinding

class DiscussionAdapter(val listener: Listener) :
    RecyclerView.Adapter<DiscussionAdapter.DiscussionHolder>() {
    val discussionList = ArrayList<Discussion>()

    class DiscussionHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = DiscussionBinding.bind(item)
        fun bind(discussion: Discussion, listener: Listener) = with(binding) {
            tvDiscussionName.text = discussion.title
            ivDiscussionIcon.setImageResource(discussion.image)
            tvUserName.text = discussion.name
            tvMessage.text = discussion.message
            itemView.setOnClickListener {
                listener.onClick(discussion.title)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscussionHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.discussion, parent, false)
        return DiscussionHolder(view)
    }

    override fun onBindViewHolder(holder: DiscussionHolder, position: Int) {
        holder.bind(discussionList[position], listener)
    }

    override fun getItemCount(): Int {
        return discussionList.size
    }

    fun addDiscussion(list: List<Discussion>) {
        discussionList.clear()
        discussionList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(chatName: String)
    }
}