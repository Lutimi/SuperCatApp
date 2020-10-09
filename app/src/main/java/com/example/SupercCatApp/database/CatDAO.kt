package com.example.SupercCatApp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.SupercCatApp.models.Cat

@Dao
interface CatDAO {

    @Insert
    fun insertCat(vararg cat: Cat)

    @Query("SELECT * FROM cats ")
    fun getAllCats(): List<Cat>

    @Delete
    fun deleteCat(vararg movie: Cat)

    @Query("SELECT * FROM cats WHERE id = :id")
    fun getACat(id: Int): Cat

}