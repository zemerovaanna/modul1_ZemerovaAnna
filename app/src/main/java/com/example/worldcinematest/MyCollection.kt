package com.example.worldcinematest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyCollection(
    @PrimaryKey(autoGenerate = true) var cId: Int? = null,
    @ColumnInfo(name = "name") var cName: String?,
    @ColumnInfo(name = "image") var cImage: Int?
)