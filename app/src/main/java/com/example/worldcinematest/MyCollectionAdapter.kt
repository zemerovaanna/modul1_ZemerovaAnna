package com.example.worldcinematest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCollectionAdapter(var list: List<MyCollection>) :
    RecyclerView.Adapter<MyCollectionAdapter.ViewHolder>() {

    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var collectionName: TextView
        var collectionImage: ImageView
        var imageIdList = listOf(
            R.drawable.heart,
            R.drawable.book,
            R.drawable.checkmark,
            R.drawable.clock,
            R.drawable.rain,
            R.drawable.coffee,
            R.drawable.compass,
            R.drawable.dollar,
            R.drawable.zipper,
            R.drawable.basketball,
            R.drawable.listen,
            R.drawable.medal,
            R.drawable.barbell,
            R.drawable.star,
            R.drawable.film,
            R.drawable.fire,
            R.drawable.potion,
            R.drawable.flower,
            R.drawable.heartbreak,
            R.drawable.smile,
            R.drawable.sad,
            R.drawable.graduationcap,
            R.drawable.key,
            R.drawable.lamp,
            R.drawable.medkit,
            R.drawable.moon,
            R.drawable.music,
            R.drawable.ninja,
            R.drawable.plane,
            R.drawable.rocket,
            R.drawable.snowflake,
            R.drawable.syringe,
            R.drawable.sun,
            R.drawable.taijitu,
            R.drawable.female,
            R.drawable.male
        )

        init {
            collectionName = view.findViewById(R.id.tvCollectionName)
            collectionImage = view.findViewById(R.id.ivCollectionIcon)
            view.setOnClickListener {
                dialog.onClick(layoutPosition)
            }
        }

        fun bind(myCollection: MyCollection) {
            collectionName.text = myCollection.cName
            collectionImage.setImageResource(myCollection.cImage!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_collection, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mycollection = list[position]
        holder.bind(mycollection)
    }
}