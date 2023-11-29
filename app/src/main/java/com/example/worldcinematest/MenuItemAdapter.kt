package com.example.worldcinematest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcinematest.databinding.ItemBinding

class MenuItemAdapter(val listener: Listener) :
    RecyclerView.Adapter<MenuItemAdapter.MebuItemHolder>() {

    val menuItemList = ArrayList<MenuItem>()

    class MebuItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemBinding.bind(item)
        fun bind(menuItem: MenuItem, listener: Listener) = with(binding) {
            itemIcon.setImageResource(menuItem.imageId)
            itemName.text = menuItem.itemName
            itemView.setOnClickListener {
                listener.onClick(menuItem.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MebuItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MebuItemHolder(view)
    }

    override fun onBindViewHolder(holder: MebuItemHolder, position: Int) {
        holder.bind(menuItemList[position], listener)
    }

    override fun getItemCount(): Int {
        return menuItemList.size
    }

    fun addDiscussion(list: List<MenuItem>) {
        menuItemList.clear()
        menuItemList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(itemId: Int)
    }
}