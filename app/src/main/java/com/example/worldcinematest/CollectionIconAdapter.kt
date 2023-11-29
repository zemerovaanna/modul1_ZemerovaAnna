package com.example.worldcinematest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinematest.databinding.IconBinding

class CollectionIconAdapter(val listener: Listener) :
    RecyclerView.Adapter<CollectionIconAdapter.CollectionIconHolder>() {

    val imageList = ArrayList<CollectionIcon>()

    class CollectionIconHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = IconBinding.bind(item)
        fun bind(icon: CollectionIcon, listener: Listener) = with(binding) {
            ivIcon.setImageResource(icon.iconId)
            itemView.setOnClickListener {
                listener.onClick(icon.iconId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionIconHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.icon, parent, false)
        return CollectionIconHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionIconHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun addImage(list: List<CollectionIcon>) {
        imageList.clear()
        imageList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(position: Int)
    }
}