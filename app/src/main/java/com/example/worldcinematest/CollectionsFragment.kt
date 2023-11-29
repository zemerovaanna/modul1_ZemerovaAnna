package com.example.worldcinematest

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CollectionsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bAdd: ImageButton
    private var list = mutableListOf<MyCollection>()
    private lateinit var adapter: MyCollectionAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collections, container, false)

        recyclerView = view.findViewById(R.id.rvCollections)
        bAdd = view.findViewById(R.id.buttonAdd)

        database = AppDatabase.getInstance(requireContext())
        adapter = MyCollectionAdapter(list)
        adapter.setDialog(object : MyCollectionAdapter.Dialog {

            override fun onClick(position: Int) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle(list[position].cName)
                dialog.setItems(
                    R.array.itemsoption,
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            0 -> {
                                val intent =
                                    Intent(requireContext(), EditorActivity::class.java)
                                intent.putExtra("id", list[position].cId)
                                intent.putExtra("title", "Изменить Коллецию")
                                startActivity(intent)
                            }

                            1 -> {
                                database.myCollectionDao().delete(list[position])
                                getData()
                            }

                            else -> {
                                dialog.dismiss()
                            }
                        }
                    })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )

        bAdd.setOnClickListener {
            startActivity(Intent(requireContext(), EditorActivity::class.java))
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        list.clear()
        list.addAll(database.myCollectionDao().getAll())
        adapter.notifyDataSetChanged()
    }
}