package com.example.worldcinematest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.worldcinematest.databinding.ActivityEditorBinding

class EditorActivity : AppCompatActivity(), CollectionIconAdapter.Listener {

    private lateinit var editorActivity: ActivityEditorBinding
    private lateinit var database: AppDatabase
    private var imageIdList = listOf(
        CollectionIcon(R.drawable.heart),
        CollectionIcon(R.drawable.book),
        CollectionIcon(R.drawable.checkmark),
        CollectionIcon(R.drawable.clock),
        CollectionIcon(R.drawable.rain),
        CollectionIcon(R.drawable.coffee),
        CollectionIcon(R.drawable.compass),
        CollectionIcon(R.drawable.dollar),
        CollectionIcon(R.drawable.zipper),
        CollectionIcon(R.drawable.basketball),
        CollectionIcon(R.drawable.listen),
        CollectionIcon(R.drawable.medal),
        CollectionIcon(R.drawable.barbell),
        CollectionIcon(R.drawable.star),
        CollectionIcon(R.drawable.film),
        CollectionIcon(R.drawable.fire),
        CollectionIcon(R.drawable.potion),
        CollectionIcon(R.drawable.flower),
        CollectionIcon(R.drawable.heartbreak),
        CollectionIcon(R.drawable.smile),
        CollectionIcon(R.drawable.sad),
        CollectionIcon(R.drawable.graduationcap),
        CollectionIcon(R.drawable.key),
        CollectionIcon(R.drawable.lamp),
        CollectionIcon(R.drawable.medkit),
        CollectionIcon(R.drawable.moon),
        CollectionIcon(R.drawable.music),
        CollectionIcon(R.drawable.ninja),
        CollectionIcon(R.drawable.plane),
        CollectionIcon(R.drawable.rocket),
        CollectionIcon(R.drawable.snowflake),
        CollectionIcon(R.drawable.syringe),
        CollectionIcon(R.drawable.sun),
        CollectionIcon(R.drawable.taijitu),
        CollectionIcon(R.drawable.female),
        CollectionIcon(R.drawable.male)
    )

    private val adapter = CollectionIconAdapter(this)
    private var imageId = R.drawable.heart
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editorActivity = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(editorActivity.root)

        database = AppDatabase.getInstance(applicationContext)
        editorActivity.rvIcons.layoutManager = GridLayoutManager(this@EditorActivity, 4)
        editorActivity.rvIcons.adapter = adapter
        adapter.addImage(imageIdList)

        editorActivity.CollectionIcon.setImageResource(imageId)

        val intent = intent.extras
        if (intent != null) {
            id = intent.getInt("id", 0)
            val mycollection = database.myCollectionDao().get(id)
            editorActivity.Title.text = intent.getString("title", "")
            editorActivity.CollectionName.setText(mycollection.cName)
            imageId = mycollection.cImage!!
            editorActivity.CollectionIcon.setImageResource(imageId)

        }

        editorActivity.ibBack.setOnClickListener {
            finish()
        }

        editorActivity.Cancel.setOnClickListener {
            editorActivity.ChangeIcon.visibility = GONE
        }

        editorActivity.buttonChangeIcon.setOnClickListener {
            editorActivity.ChangeIcon.visibility = VISIBLE
        }

        editorActivity.buttonSave.setOnClickListener {
            if (editorActivity.CollectionName.text.isNotEmpty()) {
                if (OnlyLetters(editorActivity.CollectionName.text.toString())) {
                    if (editorActivity.CollectionName.text.toString().length <= 20) {
                        if (intent != null) {
                            database.myCollectionDao().update(
                                MyCollection(
                                    id,
                                    editorActivity.CollectionName.text.toString(),
                                    imageId
                                )
                            )
                        } else {
                            database.myCollectionDao().insertAll(
                                MyCollection(
                                    null,
                                    editorActivity.CollectionName.text.toString(),
                                    imageId
                                )
                            )
                        }
                        finish()
                    } else {
                        Toast.makeText(applicationContext, R.string.title_long, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(applicationContext, R.string.only_letters, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, R.string.empty_field, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(position: Int) {
        imageId = position
        editorActivity.CollectionIcon.setImageResource(position)
    }

    fun OnlyLetters(text: String): Boolean {
        for (i in text) {
            if (!i.isLetter()) {
                return false
            }
        }
        return true
    }
}