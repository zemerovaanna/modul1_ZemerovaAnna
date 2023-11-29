package com.example.worldcinematest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface MyCollectionDao {
    @Query("SELECT * FROM myCollection")
    fun getAll(): List<MyCollection>

    @Query("SELECT * FROM myCollection WHERE cId IN (:myCollectionIds)")
    fun loadAllByIds(myCollectionIds: IntArray): List<MyCollection>

    @Insert
    fun insertAll(vararg myCollection: MyCollection)

    @Delete
    fun delete(myCollection: MyCollection)

    @Query("DELETE FROM myCollection")
    fun deleteAll()

    @Query("SELECT * FROM myCollection WHERE cId = :cId")
    fun get(cId: Int): MyCollection

    @Update
    fun update(myCollection: MyCollection)
}