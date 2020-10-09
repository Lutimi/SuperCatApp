package com.example.SupercCatApp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.SupercCatApp.models.Cat


@Database(entities = [Cat::class], version = 1)
abstract class CatDB : RoomDatabase(){
    abstract fun getCatDAO(): CatDAO

    companion object {

        private var INSTANCE : CatDB? = null

        fun getInstance(context: Context) : CatDB {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, CatDB::class.java,"cat.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as CatDB
        }
    }
}